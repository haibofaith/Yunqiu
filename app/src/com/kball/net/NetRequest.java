package com.kball.net;

import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.util.ACache;
import com.kball.util.LogUtil;
import com.kball.util.MD5Util;
import com.kball.util.SPUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 网络请求类
 * <p/>
 * <p/>
 * 可以对网络请求中的参数、返回值、耗时、文件上传过程进行打印
 * <p/>
 * 以后在此基础上还可以增加日志记录功能
 * <p/>
 * <p/>
 * <b>使用：</b>
 * <p/>
 * 在{@link android.app.Activity#onCreate(android.os.Bundle)}生成实例， 随后直接调用相应的
 * 方法即可
 * <p/>
 * <p/>
 * <p/>
 */
public class NetRequest {

	private static final String TAG = "* NetRequest *";
	private static final boolean isShowLog = true;
	private static final int DEFAULT_CACHE_TIME = 10 * 60;// 10分钟
	private static Context mContext;
	private static NetRequest request;
	private AsyncHttpClient httpClient;
	private List<Cookie> cookies;

	private NetRequest() {
		httpClient = new AsyncHttpClient();
	}

	public static void init(Application _app) {
		mContext = _app;
	}

	public static NetRequest getInstance() {
		if (null == request) {
			request = new NetRequest();
		}
		return request;
	}

	private RequestParams appendDefaultParams() {
		return appendDefaultParams(null);
	}

	private RequestParams appendDefaultParams(RequestParams params) {
		if (null == params) {
			params = new RequestParams();
		}
		// 封装head
		// params.put("userId", SPUtil.getInstance().getString(C.SP.USER_ID,
		// "")); // 用户id
		// params.put("deviceId", SPUtil.getInstance().getString(C.SP.DEVICE_ID,
		// "")); // 设备id
		// params.put("token", SPUtil.getInstance().getString(C.SP.USER_TOKEN,
		// "")); // 用户token
		return params;
	}

	/**
	 * @param url
	 *            请求url
	 * @param params
	 *            请求参数
	 * @param handler
	 *            回调
	 * @param isUseCache
	 *            是否使用缓存
	 * @param cacheTime
	 *            缓存时间，以秒为单位
	 */
	private void get(final Context ctx, final String url,
			final RequestParams params, final RequestHandler handler,
			final boolean isUseCache, final int cacheTime) {

		// if (isUseCache && WeiciApplication.isRelease) {
		String cacheData = ACache.get(mContext).getAsString(
				getCacheKey(url, appendDefaultParams(params)));
		if (!TextUtils.isEmpty(cacheData)) {
			printUseCache(url, params, cacheData);
			handler.onStart();
			try {
				handler.onSuccess_(cacheData);
			} catch (Exception e) {
				uploadExceptionLog(url, params, cacheData, e);
				e.printStackTrace();
			}
			handler.onFinish();
			return;
		}
		// }

		setRequestHead();
//		setCookie();

		httpClient.get(null != ctx ? ctx : mContext, url,
				appendDefaultParams(params), new AsyncHttpResponseHandler() {

					long startTime = 0l;

					@Override
					public void onFinish() {
						printUrlAndTime(url, startTime);
						handler.onFinish();
					}

					@Override
					public void onStart() {
						startTime = System.currentTimeMillis();
						printUrlAndParams("get", url, params);
						handler.onStart();
					}

					@Override
					public void onCancel() {
						handler.onCancel();
					}

					@Override
					public void onProgress(long bytesWritten, long totalSize) {
						printUrlAndProgress(url, bytesWritten, totalSize);
						handler.onProgress(bytesWritten, totalSize);
					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {

						saveCookies();

						String data = new String(responseBody);
						printUrlAndResponse(url, data);
						handler.customCacheData(data);
						if (isUseCache) {
							ACache.get(mContext).put(getCacheKey(url, params),
									data, cacheTime);
						}
						// 最后交给外面的程序进行处理
						try {
							handler.onSuccess_(data);
						} catch (Exception e) {
							uploadExceptionLog(url, params, data, e);
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						String response;
						if (null == responseBody) {
							response = "无明确错误信息";
						} else {
							response = new String(responseBody);
						}
						printUrlAndErrorInfo(url, statusCode, response);
						handler.onFailure(response, error);
					}
				});
	}

	/**
	 * 上传日志
	 * 
	 * @param url
	 *            出现异常时的url
	 * @param params
	 *            出现异常时的参数
	 * @param data
	 *            出现异常时返回的数据
	 */
	private void uploadExceptionLog(String url, RequestParams params,
			String data, Exception e) {
		// UrlParamsBean bean = new UrlParamsBean(url);
		// bean.params = params;
		// UploadLogUtil.uploadV1BBInterfaceError(bean, "出现了异常，异常信息为：" +
		// UploadLogUtil.exception2String(e) + " --- 出现异常返回的数据为：" + data);
	}

	public void get(Context ctx, UrlParamsBean urlParamsBean,
			final RequestHandler handler, final boolean isUseCache,
			final int cacheTime) {
		get(ctx, urlParamsBean.url, urlParamsBean.params, handler, isUseCache,
				cacheTime);
	}

	public void get(Context ctx, UrlParamsBean urlParamsBean,
			final RequestHandler handler) {
		get(ctx, urlParamsBean, handler, false, 0);
	}

	public void get(Context ctx, UrlParamsBean urlParamsBean,
			final RequestHandler handler, final int cacheTime) {
		get(ctx, urlParamsBean, handler, cacheTime > 0, cacheTime);
	}

	public void get(Context ctx, UrlParamsBean urlParamsBean,
			final RequestHandler handler, final boolean isUseCache) {
		get(ctx, urlParamsBean, handler, isUseCache, DEFAULT_CACHE_TIME);
	}

	public void post(final UrlParamsBean urlParamsBean,
			final RequestHandler handler) {
		// 1. url追加默认参数
		post(null, urlParamsBean, handler);
	}

	public void post(Context ctx, final UrlParamsBean urlParamsBean,
			final RequestHandler handler) {
		// 1. url追加默认参数
		// String requestUrl = AsyncHttpClient.getUrlWithQueryString(false,
		// urlParamsBean.url, appendDefaultParams());
		// post(ctx, requestUrl, urlParamsBean.params, handler);
		// String requestUrl = AsyncHttpClient.getUrlWithQueryString(false,
		// urlParamsBean.url, appendDefaultParams());
		post(ctx, urlParamsBean.url, urlParamsBean.params, handler);
		
	}

	private void post(final Context ctx, final String url,
			final RequestParams params, final RequestHandler handler) {

		setRequestHead();
//		setCookie();

		httpClient.post(null != ctx ? ctx : mContext, url,
				appendDefaultParams(params), new AsyncHttpResponseHandler() {

					long startTime = 0l;

					@Override
					public void onFinish() {
						printUrlAndTime(url, startTime);
						handler.onFinish();

					}

					@Override
					public void onStart() {
						startTime = System.currentTimeMillis();
						printUrlAndParams("post", url, params);
						handler.onStart();
					}

					@Override
					public void onCancel() {
						handler.onCancel();
					}

					@Override
					public void onProgress(long bytesWritten, long totalSize) {
						printUrlAndProgress(url, bytesWritten, totalSize);
						handler.onProgress(bytesWritten, totalSize);
					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {

						saveCookies();

						String data = new String(responseBody);
						printUrlAndResponse(url, data);
						try {
							handler.onSuccess_(data);
						} catch (Exception e) {
							uploadExceptionLog(url, params, data, e);
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						String response;
						if (null == responseBody) {
							response = "无明确错误信息";
						} else {
							response = new String(responseBody);
						}
						printUrlAndErrorInfo(url, statusCode, response);
						handler.onFailure(response, error);
					}
				});
	}

	public void cancelRequests(Context ctx) {
		httpClient.cancelRequests(ctx, true);
	}

	/**
	 * 打印错误信息
	 * 
	 * @param url
	 *            请求url
	 * @param response
	 *            错误信息
	 */
	private void printUrlAndErrorInfo(String url, int statusCode,
			String response) {
		LogUtil.e(isShowLog, TAG + "\nurl:" + url + "\nstatusCode:"
				+ statusCode + "\nerrorInfo:" + response);
	}

	/**
	 * 打印url和此上传数据的进度
	 * 
	 * @param url
	 *            请求url
	 * @param bytesWritten
	 *            完成的进度
	 * @param totalSize
	 *            总大小
	 */
	private void printUrlAndProgress(String url, long bytesWritten,
			long totalSize) {
		if (1 == totalSize || 0 == totalSize) {
			return;
		}
		// LogUtil.e(isShowLog, TAG + "\nurl:" + url + "\nprogress:" +
		// String.format("Progress %d from %d (%2.0f%%)", bytesWritten,
		// totalSize, (totalSize > 0) ? (bytesWritten * 1.0 / totalSize) * 100 :
		// -1));
	}

	/**
	 * 打印使用缓存数据
	 * 
	 * @param url
	 *            请求url
	 * @param params
	 *            请求参数
	 * @param cacheData
	 *            缓存数据
	 */
	private void printUseCache(String url, RequestParams params,
			String cacheData) {
		LogUtil.e(isShowLog,
				TAG + "\nurl:" + url + "\nparams:" + params.toString()
						+ "\ncache:" + cacheData);
	}

	/**
	 * 打印url和此请求的耗时
	 * 
	 * @param url
	 *            请求url
	 * @param startTime
	 *            url启动时间
	 */
	private void printUrlAndTime(String url, long startTime) {
		LogUtil.e(isShowLog,
				TAG + "\nurl:" + url + " - time:"
						+ (System.currentTimeMillis() - startTime) + "ms");
	}

	/**
	 * 打印url和此请求的返回值
	 * 
	 * @param url
	 *            请求url
	 * @param responseBody
	 *            url的返回内容
	 */
	private void printUrlAndResponse(String url, String responseBody) {
		LogUtil.e(isShowLog, TAG + "\nurl:" + url + "\nresponse:"
				+ responseBody);
	}

	/**
	 * 打印url和此请求的参数
	 * 
	 * @param method
	 *            请求方法
	 * @param url
	 *            请求url
	 * @param params
	 *            请求参数
	 */
	private void printUrlAndParams(String method, String url,
			RequestParams params) {
		StringBuilder sb = new StringBuilder();
		sb = sb.append(TAG).append("\nurl:").append(url).append("\nmethod:")
				.append(method).append("\nparams:");
		if (null == params || TextUtils.isEmpty(params.toString())) {
			sb.append("无");
		} else {
			sb.append(params.toString());
		}

		LogUtil.e(isShowLog, sb.toString());
	}

	/**
	 * 得到缓存key值
	 * 
	 * @param url
	 *            请求url
	 * @param params
	 *            请求参数
	 * @return 缓存此请求数据的key
	 */
	private String getCacheKey(String url, RequestParams params) {
		if (null == params) {
			params = new RequestParams();
		}
		return MD5Util.generate(url + params.toString());
	}

	/**
	 * 保存cookie
	 */
	private void saveCookies() {
		BasicCookieStore basicCookieStore = (BasicCookieStore) httpClient
				.getHttpContext().getAttribute(ClientContext.COOKIE_STORE);
		if (null != basicCookieStore) {
			cookies = basicCookieStore.getCookies();
		}
		SPUtil.getInstance()
				.putString(C.SP.COOKIES, new Gson().toJson(cookies));
	}

	/**
	 * 设置cookie
	 */
	private void setCookie() {

		CookieStore cookieStore = new BasicCookieStore();
		if (0 != cookies.size()) {
			for (Cookie cookie : cookies) {
				cookieStore.addCookie(cookie);
			}
		} else {
			String c = SPUtil.getInstance().getString(C.SP.COOKIES, "");
			if (!TextUtils.isEmpty(c)) {
				Type listType = new TypeToken<List<BasicClientCookie>>() {
				}.getType();
				cookies = new Gson().fromJson(c, listType);
				for (Cookie cookie : cookies) {
					cookieStore.addCookie(cookie);
				}
			}
		}

		if (null != cookieStore.getCookies()
				&& 0 != cookieStore.getCookies().size()) {
			httpClient.getHttpContext().removeAttribute(
					ClientContext.COOKIE_STORE);
			httpClient.setCookieStore(cookieStore);
		}

	}

	/**
	 * 设置请求头信息
	 */
	private void setRequestHead() {
		/**
		 * (1)、resouce 来源、version 版本号、channel 渠道号、deviceId
		 * 设备ID、token手机token统一存放在head传输。
		 */
//		httpClient.addHeader("resource", "android");
//		httpClient.addHeader("version", YxfzApplication.mAppVersion);
//		httpClient.addHeader("channel", YxfzApplication.mUmengChannel);
		httpClient.addHeader("access_token",
				SPUtil.getInstance().getString(C.SP.ACCESS_TOKEN, ""));
		httpClient.addHeader("user_id",
				SPUtil.getInstance().getString(C.SP.USER_ID, ""));
	}

}
