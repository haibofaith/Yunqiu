package com.kball;

/**
 * 常量类
 * <p/>
 * wxl --2017-1-12
 * <p/>
 */
public final class C {
	private C() {
	}

	public static final class SP {

		/**
		 * int 屏幕宽
		 */
		public static final String SCREEN_WIDTH = "screen_w";
		/**
		 * int 屏幕高
		 */
		public static final String SCREEN_HEIGHT = "screen_h";
		public static final String COOKIES = "cookies";
		public static final String SP_NAME = "kball_sp";
		public static final String APP_VERSION = "app_version";
		public static final String USER_TOKEN = "user_token";
		public static final String DEVICE_ID = "device_id";
		public static final String SERVER_IP = "server_ip";
		/**
		 * UseInfo
		 * */
		public static final String USER_INFO = "user_info";
		public static final String ACCESS_TOKEN = "access_token";
		public static final String EXPIRES_IN = "expires_in";
		public static final String REFRESH_TOKEN = "refresh_token";
		public static final String BOUND_STATUS = "bound_status";
		public static final String OPENID = "openid";
		public static final String USER_ID = "user_id";

		public static final String USER_ICON = "user_icon";
		public static final String USER_NAME = "user_name";

		public static final String OPEN_ID_TYPE = "openIdType";//微信或者QQ登陆

		public static final String IMG_URL = "http://picture-services.b0.upaiyun.com/";
		public static final String IMG_URL_YPY = "http://picture-services.b0.upaiyun.com/";

		/**
		 * 自定义
		 * */
		public static final int LoginCode = 10000;
		public static final int BindLoginCode = 10001;
		public static final int SelectTeamCode = 10002;
		public static final int PerfectLoginCode = 10003;
		public static final int CREATE_RANK = 1;
		public static final int EDIT_RANK = 2;

		//又拍云常量
		//空间名
		public static String SPACE = "picture-services";
		//操作员
		public static String OPERATER = "mengqingyu";
		//密码
		public static String PASSWORD = "Pw123123";
		//保存路径
		public static String SAVEPATH = "uploads/{year}{mon}{day}/{random32}{.suffix}";
	}
}
