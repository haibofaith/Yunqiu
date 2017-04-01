package com.kball;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import com.kball.net.NetRequest;
import com.kball.util.LogUtil;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import cn.sharesdk.framework.ShareSDK;


public class KballApplication extends Application {

	public static final boolean isRelease = false;
	public static String currentUserNick = "";
	private static KballApplication app;
	private static Context mContext;

	public synchronized static KballApplication getInstance() {
		return app;
	}

	public synchronized static Context getContext() {
		return mContext;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		initConfig();
		ToastAlone.init(this);
		NetRequest.init(this);
		ShareSDK.initSDK(this);
		//极光推送
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);
		app = this;
		mContext = getApplicationContext();

		DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisk(true).build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).defaultDisplayImageOptions(imageOptions)
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.memoryCacheSize(2 * 2048 * 2048)
				.memoryCache(new WeakMemoryCache()).build();
		ImageLoader.getInstance().init(config);

	}

	private String getVersion() {
		try {
			return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 程序初始化配置<br>
	 * </br> 日志：配置是否使用，及默认使用的tag<br>
	 * </br> 错误信息反馈：<br>
	 * </br> imageloader：配置此图片加载器默认使用的一下信息，针对项目可更改<br>
	 * </br> SharedPreferences：配置SharedPreferences的文件名
	 */
	private void initConfig() {
		initConfig(true, true, true, true);
	}

	/**
	 * 程序初始化配置<br>
	 * </br>
	 */
	private void initConfig(boolean isConfigLog, boolean isConfigError,
			boolean isConfigImageloader, boolean isConfigSharedPreferences) {
		// 程序log配置
		if (isConfigLog) {
			LogUtil.init(!isRelease, "kball");
		}

		if (isConfigSharedPreferences) {
			SPUtil.init(this, C.SP.SP_NAME);
		}
	}

}
