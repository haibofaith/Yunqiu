package com.kball.util;

import java.io.File;
import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

public class Util {
	public static File cameraFile;

	/**
	 * 获取屏幕高度和宽带
	 * 
	 * @param mContext
	 * @return int[高，宽]
	 */
	public static int[] getScreen(Context mContext) {
		DisplayMetrics dm = new DisplayMetrics();
		// 取得窗口属性
		// getWindowManager().getDefaultDisplay().getMetrics(dm);
		((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);

		// 窗口的宽度
		int screenWidth = dm.widthPixels;

		// 窗口高度
		int screenHeight = dm.heightPixels;
		int screen[] = { screenHeight, screenWidth };
		return screen;

	}

	public static int getSecreenW(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		dm = null;
		return width;
	}

	/**
	 * 
	 * @Title: dip2px @Description: TODO(dp转px) @param @param
	 *         context @param @param dpValue @param @return 设定文件 @return int
	 *         返回类型 @throws
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}







	// 返回是否有SD卡
	public static boolean GetSDState() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @Title: deleteFile @Description: TODO(删除文件) @param @param file
	 *         设定文件 @return void 返回类型 @throws
	 */
	public static void deleteFile(File file) {
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				file.delete(); // delete()方法 你应该知道 是删除的意思;
			} else if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
				}
			}
			file.delete();
		} else {

		}
	}

	/**
	 * 显示toast
	 * 
	 * @param context
	 *            当前activity
	 * @param content
	 *            显示的内容
	 */

	public static void showToast(Context mContext, String content) {
		Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
	}

	/**
	 * actionbar高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 38;// 默认为38，貌似大部分是这样的

		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = context.getResources().getDimensionPixelSize(x);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return sbar;
	}


	/**
	 * 获取时间戳
	 * @return
	 */
	public static long getSeq() {
		return System.currentTimeMillis();
	}

	/**
	 * 检查网络状态
	 */
	public static boolean isNetUseable(Context context) {
		boolean have=false;
		ConnectivityManager cwjManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE); 
		NetworkInfo info = cwjManager.getActiveNetworkInfo(); 
		if (info != null && info.isAvailable()){ 
			have=true;
		} 
		else
		{
		
			showToast(context, "无网络连接");
		}
		return have;
	}

}
