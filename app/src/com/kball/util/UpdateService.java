/**   
 * @Title: UpdateService.java 
 * @Package com.elong.crm.ntv.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author Siyu.Jiang  
 * @date 2014年5月13日 下午6:12:19 
 */
package com.kball.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.RemoteViews;


import com.kball.R;
import com.kball.function.home.ui.HomeActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

/**
 * @ClassName: UpdateService
 * @Description: 自动更新
 * @author Siyu.Jiang
 * @date 2014年5月13日 下午6:12:19
 * 
 */
public class UpdateService extends Service {

	// 文件存储
	private File updateDir = null;
	private File updateFile = null;
	public static final String PATH_SDCARD = "/sdcard";
	public static final String PATH_CRM = PATH_SDCARD + "/SalesCRM";
	public static final String KEY_UPDATE_PATH = "updatePath";
	public static final String ACTION_UPDATE_RECEIVER1 = "com.kball.util.UpdateReceiver";

	// 通知栏
	private NotificationManager updateNotificationManager = null;
	private Notification updateNotification = null;
	// 通知栏跳转Intent
	private Intent updateIntent = null;
	private PendingIntent updatePendingIntent = null;

	private RemoteViews mRemoteViews;
	private StringBuffer mStringBuffer = new StringBuffer();

	private String updatePath;
	private String storagePath = PATH_CRM;
	private static final String ACTION_UPDATE_RECEIVER = ACTION_UPDATE_RECEIVER1;

	private static final int UPDATE_NOTIFICATION_ID = 10001;

	@SuppressWarnings("deprecation")
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

	    if(intent != null)
	    {
    		// 获取传值
    		updatePath = intent.getStringExtra(KEY_UPDATE_PATH);
    		// updatePath =
    		// "http://softfile.3g.qq.com:8080/msoft/179/1105/10753/MobileQQ1.0(Android)_Build0198.apk";
    		// 创建文件
    		if (android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment.getExternalStorageState())) {
    			updateDir = new File(Environment.getExternalStorageDirectory(), storagePath);
    			updateFile = new File(updateDir.getPath(), getResources().getString(R.string.app_name) + ".apk");
    		}
    
    		this.updateNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    		this.updateNotification = new Notification(R.drawable.ic_download, "云球开始下载了", System.currentTimeMillis());
    
    		mRemoteViews = new RemoteViews("com.kball", R.layout.view_update_notification);
    		mRemoteViews.setImageViewResource(R.id.image_icon, R.drawable.ic_launcher);
    		updateNotification.contentView = mRemoteViews;
    
    		// 设置下载过程中，点击通知栏，回到主界面
    		updateIntent = new Intent(Intent.ACTION_MAIN);
    
    		// updateIntent.setAction(Intent.ACTION_MAIN);
    
    		updateIntent.addCategory(Intent.CATEGORY_LAUNCHER);
    		updateIntent.setClass(this, HomeActivity.class);
    		updateIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
    		updateNotification.flags = Notification.FLAG_ONGOING_EVENT; // 设置常驻Flag
    		updatePendingIntent = PendingIntent.getActivity(this, 0, updateIntent, 0);
    		updateNotification.contentIntent = updatePendingIntent;
    		// 设置通知栏显示内容
    		// 发出通知
    		updateNotificationManager.notify(UPDATE_NOTIFICATION_ID, updateNotification);
    		sendBroadcast4Percent(0, null);
    
    		new Thread(new updateRunnable()).start();// 下载过程
    
	    }
	    return super.onStartCommand(intent, flags, startId);
	}

	private final static int DOWNLOAD_COMPLETE = 0;
	private final static int DOWNLOAD_FAIL = 1;
	private Handler updateHandler = new Handler() {
		@SuppressWarnings("deprecation")
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWNLOAD_COMPLETE:
				// 点击安装PendingIntent
				Uri uri = Uri.fromFile(updateFile);
				Intent installIntent = new Intent(Intent.ACTION_VIEW);
				installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				installIntent.setDataAndType(uri, "application/vnd.android.package-archive");
				updatePendingIntent = PendingIntent.getActivity(UpdateService.this, 0, installIntent, 0);

				// 铃声提醒
				// updateNotification.defaults = Notification.DEFAULT_SOUND;
				updateNotification = new Notification(R.drawable.ic_download, "下载完成", System.currentTimeMillis());
				updateNotification.flags = Notification.FLAG_AUTO_CANCEL;// 设置flag
				updateNotification.contentView = mRemoteViews;
				updateNotification.contentIntent = updatePendingIntent;
				updateProgress(100, updatePath, getResources().getString(R.string.app_name), System.currentTimeMillis());
				updateNotificationManager.cancel(UPDATE_NOTIFICATION_ID);
				updateNotificationManager.notify(UPDATE_NOTIFICATION_ID, updateNotification);
				sendBroadcast4Percent(100, updateFile.getPath());

				// 停止服务
				stopSelf();
				break;
			case DOWNLOAD_FAIL:
				updateNotification.flags = Notification.FLAG_AUTO_CANCEL;
				sendBroadcast4Percent(-1, updateFile.getPath());
				updateNotificationManager.cancel(UPDATE_NOTIFICATION_ID);
				stopSelf();
				break;
			default:
				stopSelf();
			}
		}
	};

	class updateRunnable implements Runnable {
		Message message = updateHandler.obtainMessage();

		public void run() {
			message.what = DOWNLOAD_COMPLETE;
			try {
				// 增加权限;
				if (!updateDir.exists()) {
					updateDir.mkdirs();
				}
				if (!updateFile.exists()) {
					updateFile.createNewFile();
				}
				// 下载函数
				// 增加权限;
				long downloadSize = downloadUpdateFile(updatePath, updateFile);
				if (downloadSize > 0) {
					// 下载成功
					updateHandler.sendMessage(message);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				message.what = DOWNLOAD_FAIL;
				// 下载失败
				updateHandler.sendMessage(message);
			}
		}
	}

	public long downloadUpdateFile(String downloadUrl, File saveFile) throws Exception {
		int downloadCount = 0;
		int currentSize = 0;
		long totalSize = 0;
		int updateTotalSize = 0;

		HttpURLConnection httpConnection = null;
		InputStream is = null;
		FileOutputStream fos = null;

		try {
			URL url = new URL(downloadUrl);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestProperty("User-Agent", "PacificHttpClient");
			if (currentSize > 0) {
				httpConnection.setRequestProperty("RANGE", "bytes=" + currentSize + "-");
			}
			httpConnection.setConnectTimeout(10000);
			httpConnection.setReadTimeout(20000);
			updateTotalSize = httpConnection.getContentLength();
			if (httpConnection.getResponseCode() == 404) {
				throw new Exception("fail!");
			}
			is = httpConnection.getInputStream();
			fos = new FileOutputStream(saveFile, false);
			byte buffer[] = new byte[4096];
			int readsize = 0;
			while ((readsize = is.read(buffer)) > 0) {
				fos.write(buffer, 0, readsize);
				totalSize += readsize;
				// 百分比增加3通知一次
				if ((downloadCount == 0) || (int) (totalSize * 100 / updateTotalSize) - 3 > downloadCount) {
					downloadCount += 3;
					updateProgress(downloadCount, updatePath, getResources().getString(R.string.app_name), System.currentTimeMillis());
					sendBroadcast4Percent(downloadCount, null);
				}
			}
		} finally {
			if (httpConnection != null) {
				httpConnection.disconnect();
			}
			if (is != null) {
				is.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
		return totalSize;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private void updateProgress(int percent, String url, String packageName, long when) {

		if (mStringBuffer != null)
			mStringBuffer.delete(0, mStringBuffer.length());
		else {
			mStringBuffer = new StringBuffer();
		}
		if (percent != 100) {
			mStringBuffer.append(percent).append("%");
			mRemoteViews.setProgressBar(R.id.progress, 100, percent, false);
			mRemoteViews.setTextViewText(R.id.text_percent, mStringBuffer.toString());
		} else {
			mStringBuffer.append(getText(R.string.download_complete));
			mRemoteViews.setTextViewText(R.id.text_percent, mStringBuffer.toString());
			mRemoteViews.setViewVisibility(R.id.progress, View.INVISIBLE);
		}
		if (url != null)
			mRemoteViews.setTextViewText(R.id.text_url, url);
		if (packageName != null)
			mRemoteViews.setTextViewText(R.id.text_pacagename, packageName);
		if (when != -1) {
			java.util.Date date = new java.util.Date(when);
			mRemoteViews.setTextViewText(R.id.text_time, new SimpleDateFormat("HH:mm").format(date));
		}
		updateNotificationManager.notify(UPDATE_NOTIFICATION_ID, updateNotification);
	}

	private void sendBroadcast4Percent(int percent, String filePath) {
		Intent intent = new Intent(ACTION_UPDATE_RECEIVER);
		intent.putExtra("percent", percent);
		if (filePath != null)
			intent.putExtra("filePath", filePath);
		sendBroadcast(intent);
	}

}
