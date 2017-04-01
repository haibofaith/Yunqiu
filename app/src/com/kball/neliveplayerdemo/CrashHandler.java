package com.kball.neliveplayerdemo;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.StatFs;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.
 *
 * @author user
 *
 */
@SuppressLint("SdCardPath")
public class CrashHandler implements UncaughtExceptionHandler {

	private static final String TAG = "NELPCrashHandler";

	public final static long K = 1024;
	public final static long M = 1024 * 1024;
	// 外置存储卡默认预警临界值
    private static final long THRESHOLD_WARNING_SPACE = 100 * M;
	// 保存文件时所需的最小空间的默认值
	public static final long THRESHOLD_MIN_SPCAE = 60 * M;
	
	// CrashHandler 实例
	private static CrashHandler INSTANCE = new CrashHandler();

	// 程序的 Context 对象
	private Context mContext;

	// 系统默认的 UncaughtException 处理类
	private Thread.UncaughtExceptionHandler mDefaultHandler;

	// 用来存储设备信息和异常信息
	private Map<String, String> infos = new HashMap<String, String>();

	// 用来显示Toast中的信息
	private static String error = "程序崩溃！！";

	// 用于格式化日期,作为日志文件名的一部分
	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA);

	/** 保证只有一个 CrashHandler 实例 */
	private CrashHandler() {
		         //
	}

	/** 获取 CrashHandler 实例 ,单例模式 */
	public static CrashHandler getInstance() {
		return INSTANCE;
	}

	/**
	 * 获取外置存储卡剩余空间
	 * @return
	 */
    public long getAvailableExternalSize(String directoryPath) {
    	try {
            StatFs sf = new StatFs(directoryPath);
            long blockSize = sf.getBlockSize();
            long availCount = sf.getAvailableBlocks();
            long availCountByte = availCount * blockSize;
            return availCountByte;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
	}

	/**
     * 判断外部存储是否存在，以及是否有足够空间保存指定类型的文件
     *
     * @param fileType
     * @return false: 无存储卡或无空间可写, true: 表示ok
     */
    public boolean hasEnoughSpaceForWrite(String directoryPath) {
        long residual = getAvailableExternalSize(directoryPath);
        if (residual < THRESHOLD_MIN_SPCAE) {
            return false;
        } else if (residual < THRESHOLD_WARNING_SPACE) {

        }

        return true;
    }

	 /**
	 * 初始化
	 *
	 * @param context
	 */
	 public void init(Context context) {
		 mContext = context;

		 // 获取系统默认的 UncaughtException 处理器
		 mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

		 // 设置该 CrashHandler 为程序的默认处理器
		 Thread.setDefaultUncaughtExceptionHandler(this);
	 }

	 /**
	 * 当 UncaughtException 发生时会转入该函数来处理
	 */
	 @Override
	 public void uncaughtException(Thread thread, Throwable ex) {
		 if (!handleException(ex) && mDefaultHandler != null) {
			// 如果用户没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(thread, ex);
		 } else {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Log.e(TAG, "error : ", e);
			}

			// 退出程序
			android.os.Process.killProcess(android.os.Process.myPid());
			// mDefaultHandler.uncaughtException(thread, ex);
			System.exit(1);
		 }
	 }

	 /**
	 * 自定义错误处理，收集错误信息，发送错误报告等操作均在此完成
	 *
	 * @param ex
	 * @return true：如果处理了该异常信息；否则返回 false
	 */
	 private boolean handleException(Throwable ex) {
		if (ex == null) {
			return false;
		}

		// 收集设备参数信息
		collectDeviceInfo(mContext);
		// 保存日志文件
		saveCrashInfo2File(ex);
		// 使用 Toast 来显示异常信息
		new Thread() {
			@Override
			public void run() {
			 Looper.prepare();
			 Toast.makeText(mContext, error, Toast.LENGTH_LONG).show();
			 Looper.loop();
			}
		     }.start();
		return true;
	 }

	 /**
	 * 收集设备参数信息
	 *
	 * @param ctx
	 */
	 public void collectDeviceInfo(Context ctx) {
		try {
			PackageManager pm = ctx.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
			PackageManager.GET_ACTIVITIES);

			if (pi != null) {
				String versionName = pi.versionName == null ? "null" : pi.versionName;
				String versionCode = pi.versionCode + "";
				infos.put("versionName", versionName);
				infos.put("versionCode", versionCode);
			}
		} catch (NameNotFoundException e) {
			Log.e(TAG, "an error occured when collect package info", e);
		}

		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				infos.put(field.getName(), field.get(null).toString());
				Log.d(TAG, field.getName() + " : " + field.get(null));
			} catch (Exception e) {
				Log.e(TAG, "an error occured when collect crash info", e);
			}
		}
	 }

	 /**
	 * 删除单个文件
	 * @param   filePath    被删除文件的文件名
	 * @return 文件删除成功返回true，否则返回false
	 */
	 public boolean deleteFile(String filePath) {
	     File file = new File(filePath);
	     if (file.isFile() && file.exists()) {
	        return file.delete();
	     }
	     return false;
	 }

	 /**
	 * 删除文件夹下的文件
	 * @param   filePath 被删除目录下文件的路径
	 * @return  目录删除成功返回true，否则返回false
	 */
	 public boolean deleteDirectory(String filePath) {
	     boolean flag = false;

	     //如果filePath不以文件分隔符结尾，自动添加文件分隔符
	     if (!filePath.endsWith(File.separator)) {
	         filePath = filePath + File.separator;
	     }

	     File dirFile = new File(filePath);
	     if (!dirFile.exists() || !dirFile.isDirectory()) {
	         return false;
	     }

	     flag = true;
	     File[] files = dirFile.listFiles();

	     //遍历删除文件夹下的所有文件(包括子目录)
	     for (int i = 0; i < files.length; i++) {
	         if (files[i].isFile()) {
	             //删除子文件
	             flag = deleteFile(files[i].getAbsolutePath());
	             if (!flag) break;
	         }
	     }

	     if (!flag) return false;

	     return true;
	 }

	 /**
	 * 保存错误信息到文件中 *
	 *
	 * @param ex
	 * @return 返回文件名称,便于将文件传送到服务器
	 */
	 private String saveCrashInfo2File(Throwable ex) {
		StringBuffer sb = getTraceInfo(ex);

		for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");  
        } 
		
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();

		String result = writer.toString();
		sb.append(result);
		try {
			long timestamp = System.currentTimeMillis();
			String time = formatter.format(new Date());
			String fileName = "crash-" + time + "-" + timestamp + ".log";

			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				String path = Environment.getExternalStorageDirectory() + "/crash/";
				File dir = new File(path);
				
				if (!dir.exists()) {
					dir.mkdirs();
				}
				else if(!hasEnoughSpaceForWrite(path))
				{
					deleteDirectory(path);
				}
				
				FileOutputStream fos = new FileOutputStream(path + fileName);
				fos.write(sb.toString().getBytes());
				fos.close();
			}

			return fileName;
		} catch (Exception e) {
			Log.e(TAG, "an error occured while writing file...", e);
		}

		return null;
	 }

	 /**
	 * 整理异常信息
	 * @param e
	 * @return
	 */
	 public static StringBuffer getTraceInfo(Throwable e) {
		StringBuffer sb = new StringBuffer();

		Throwable ex = e.getCause() == null ? e : e.getCause();
		StackTraceElement[] stacks = ex.getStackTrace();
		for (int i = 0; i < stacks.length; i++) {
			sb.append("class: ").append(stacks[i].getClassName())
					.append("; method: ").append(stacks[i].getMethodName())
					.append("; line: ").append(stacks[i].getLineNumber())
			        .append(";  Exception: ").append(ex.toString() + "\n");
		}
		return sb;
	 }
}