package com.kball.neliveplayerdemo.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.R;
import com.kball.function.Match.views.HeheView;
import com.kball.neliveplayerdemo.NEMediaController;
import com.kball.neliveplayerdemo.NEVideoView;
import com.netease.neliveplayer.NEMediaPlayer;

import java.util.List;

public class NEVideoPlayerActivity2 extends RelativeLayout {
	public final static String TAG = "NELivePlayer/NEVideoPlayerActivity";
	public NEVideoView mVideoView;  //用于画面显示
	private View mLoadingView; //用于指示缓冲状态
	private NEMediaController mMediaController; //用于控制播放

	public static final int NELP_LOG_UNKNOWN = 0; //!< log输出模式：输出详细
	public static final int NELP_LOG_DEFAULT = 1; //!< log输出模式：输出详细
	public static final int NELP_LOG_VERBOSE = 2; //!< log输出模式：输出详细
	public static final int NELP_LOG_DEBUG   = 3; //!< log输出模式：输出调试信息
	public static final int NELP_LOG_INFO    = 4; //!< log输出模式：输出标准信息
	public static final int NELP_LOG_WARN    = 5; //!< log输出模式：输出警告
	public static final int NELP_LOG_ERROR   = 6; //!< log输出模式：输出错误
	public static final int NELP_LOG_FATAL   = 7; //!< log输出模式：一些错误信息，如头文件找不到，非法参数使用
	public static final int NELP_LOG_SILENT  = 8; //!< log输出模式：不输出

	private String mVideoPath; //文件路径
	private String mDecodeType;//解码类型，硬解或软解
	private String mMediaType; //媒体类型
	private boolean mHardware = true;
	private ImageButton mPlayBack;
	private TextView mFileName; //文件名称
	private String mTitle;
	private Uri mUri;
	private boolean pauseInBackgroud = false;

	private RelativeLayout mPlayToolbar;

	NEMediaPlayer mMediaPlayer = new NEMediaPlayer();

	Context mContext ;
	HeheView mImpl;
	int type ;
	public NEVideoPlayerActivity2(Context context, String media_type, String decode_type, String videoPath, HeheView
			mImpl,int type) {
		super(context);
		mContext = context;
		mMediaType = media_type;
		mDecodeType = decode_type;
		mVideoPath = videoPath;
		this.mImpl = mImpl;
		this.type = type;
		init();
	}

	private void init() {

		View v = View.inflate(mContext,R.layout.activity_player2,null);

		//接收MainActivity传过来的参数



		if (mDecodeType.equals("hardware")) {
			mHardware = true;
		}
		else if (mDecodeType.equals("software")) {
			mHardware = false;
		}

		mPlayBack = (ImageButton)v.findViewById(R.id.player_exit);//退出播放
		mPlayBack.getBackground().setAlpha(0);
		mFileName = (TextView)v.findViewById(R.id.file_name);

		mUri = Uri.parse(mVideoPath);
		if (mUri != null) { //获取文件名，不包括地址
			List<String> paths = mUri.getPathSegments();
			String name = paths == null || paths.isEmpty() ? "null" : paths.get(paths.size() - 1);
			setFileName(name);
		}

		mPlayToolbar = (RelativeLayout)v.findViewById(R.id.play_toolbar);
		mPlayToolbar.setVisibility(View.INVISIBLE);

		mLoadingView = v.findViewById(R.id.buffering_prompt);
		mMediaController = new NEMediaController(mContext,type);

		mVideoView = (NEVideoView) v.findViewById(R.id.video_view);

		if (mMediaType.equals("livestream")) {
			mVideoView.setBufferStrategy(0); //直播低延时
		}
		else {
			mVideoView.setBufferStrategy(2); //点播抗抖动
		}
		mVideoView.setMediaController(mMediaController);
		mVideoView.setBufferingIndicator(mLoadingView);
		mVideoView.setMediaType(mMediaType);
		mVideoView.setHardwareDecoder(mHardware);
		mVideoView.setPauseInBackground(pauseInBackgroud);
		mVideoView.setVideoPath(mVideoPath);
		mMediaPlayer.setLogLevel(NELP_LOG_SILENT); //设置log级别
		mVideoView.requestFocus();
		mVideoView.start();

		mPlayBack.setOnClickListener(mOnClickEvent); //监听退出播放的事件响应
		mMediaController.setOnShownListener(mOnShowListener); //监听mediacontroller是否显示
		mMediaController.setOnHiddenListener(mOnHiddenListener); //监听mediacontroller是否隐藏
		addView(v);
	}

	OnClickListener mOnClickEvent = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.player_exit) {
				mVideoView.release_resource();
//				mContext.finish();
				mImpl.close();
			}
		}
	};

	NEMediaController.OnShownListener mOnShowListener = new NEMediaController.OnShownListener() {

		@Override
		public void onShown() {
			mPlayToolbar.setVisibility(View.VISIBLE);
			mPlayToolbar.requestLayout();
			mVideoView.invalidate();
			mPlayToolbar.postInvalidate();
		}
	};

	NEMediaController.OnHiddenListener mOnHiddenListener = new NEMediaController.OnHiddenListener() {

		@Override
		public void onHidden() {
			mPlayToolbar.setVisibility(View.INVISIBLE);
		}
	};

	public void setFileName(String name) { //设置文件名并显示出来
		mTitle = name;
		if (mFileName != null)
			mFileName.setText(mTitle);

		mFileName.setGravity(Gravity.CENTER);
	}

}