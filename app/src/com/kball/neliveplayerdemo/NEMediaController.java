/*
 * Copyright (C) 2006 The Android Open Source Project
 * Copyright (C) 2015 netease
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kball.neliveplayerdemo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


import com.kball.R;
import com.kball.util.Util;

import java.util.Locale;

/**
 * A view containing controls for a MediaPlayer. Typically contains the
 * buttons like "Play/Pause", "Rewind", "Fast Forward" and a progress
 * slider. It takes care of synchronizing the controls with the state
 * of the MediaPlayer.
 * <p>
 * The way to use this class is to instantiate it programmatically.
 * The MediaController will create a default set of controls
 * and put them in a window floating above your application. Specifically,
 * the controls will float above the view specified with setAnchorView().
 * The window will disappear if left idle for three seconds and reappear
 * when the user touches the anchor view.
 * <p>
 * Functions like show() and hide() have no effect when MediaController
 * is created in an xml layout.
 *
 * MediaController will hide and
 * show the buttons according to these rules:
 * <ul>
 * <li> The "previous" and "next" buttons are hidden until setPrevNextListeners()
 *   has been called
 * <li> The "previous" and "next" buttons are visible but disabled if
 *   setPrevNextListeners() was called with null listeners
 * <li> The "rewind" and "fastforward" buttons are shown unless requested
 *   otherwise by using the MediaController(Context, boolean) constructor
 *   with the boolean set to false
 * </ul>
 */
public class NEMediaController extends FrameLayout {
    private static final String TAG = "NELivePlayer/NEMediaController";

    private MediaPlayerControl mPlayer;
    private Context mContext;
    private PopupWindow mWindow;
    private int mAnimStyle;
    private View mAnchor;
    private View mRoot;
    private ProgressBar mProgress;
    private TextView mEndTime, mCurrentTime;
    private TextView mFileName;
    private String mTitle;
    private long mDuration;
    private boolean mShowing;
    private boolean mDragging;
    private boolean mInstantSeeking = true;
    private static final int sDefaultTimeout = 3000;//3000;
    private static final int FADE_OUT = 1;
    private static final int SHOW_PROGRESS = 2;
    private boolean mFromXml = false;
    private ImageView mPauseButton;
    private ImageView mSetPlayerScaleButton;
    private ImageView mSnapshotButton;
    private ImageView mMuteButton;
    
    private boolean mute_flag = false;
    private boolean mPaused = false;
    private boolean mIsFullScreen = false;
    
    private int mVideoScalingMode = VIDEO_SCALING_MODE_FIT;
    public static final int VIDEO_SCALING_MODE_NONE = 0;
    public static final int VIDEO_SCALING_MODE_FIT  = 1;
    public static final int VIDEO_SCALING_MODE_FILL = 2;
    public static final int VIDEO_SCALING_MODE_FULL = 3;
    int type = 0;


    //通过Context对象和AttributeSet对象来创建MediaController对象
    public NEMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRoot = this;
        mFromXml = true;
        initController(context);
    }

    //通过Context来创建MediaController对象
    public NEMediaController(Context context,int type) {
        super(context);
        this.type = type;
        if (!mFromXml && initController(context))
            initFloatingWindow();
    }

    private boolean initController(Context context) {
        mContext = context;
        return true;
    }

    @Override
    //从XML加载完所有子视图后调用。初始化控制视图（调用initControllerView方法，设置事件、绑定控件和设置默认值）
    public void onFinishInflate() {
        if (mRoot != null)
            initControllerView(mRoot);
    }

    private void initFloatingWindow() {
        mWindow = new PopupWindow(mContext);
        mWindow.setFocusable(false);
        mWindow.setBackgroundDrawable(null);
        mWindow.setOutsideTouchable(true);
        mAnimStyle = android.R.style.Animation;
    }

    /**
     * 设置MediaController绑定到一个视图上。例如可以是一个VideoView对象，或者是你的activity的主视图。
     * 
     * @param view
     *            view	可见时绑定的视图
     */
    public void setAnchorView(View view) {
        mAnchor = view;
        if (!mFromXml) {
            removeAllViews();
            mRoot = makeControllerView();
            mWindow.setContentView(mRoot);
            mWindow.setWidth(LayoutParams.MATCH_PARENT);
            mWindow.setHeight(LayoutParams.WRAP_CONTENT);
        }
        initControllerView(mRoot);
    }

    /**
     * Create the view that holds the widgets that control playback. Derived
     * classes can override this to create their own.
     * 
     * @return The controller view.
     */
    protected View makeControllerView() {
        return ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.mediacontroller, this);
    }

    private void initControllerView(View v) {
        mPauseButton = (ImageView) v.findViewById(R.id.mediacontroller_play_pause); //播放暂停按钮
        if (mPauseButton != null) {
        	if (mPaused) {
        		mPauseButton.setImageResource(R.drawable.nemediacontroller_pause);
        		mPlayer.pause();
        	}
            mPauseButton.requestFocus();
            mPauseButton.setOnClickListener(mPauseListener);
        }
        
        mSetPlayerScaleButton = (ImageView) v.findViewById(R.id.video_player_scale);  //画面显示模式按钮
        if(mSetPlayerScaleButton != null) {
        	if (mPlayer.isHardware() && mPlayer.isInBackground()) {
				switch(mVideoScalingMode)
				{
				case VIDEO_SCALING_MODE_FIT:
					mVideoScalingMode = VIDEO_SCALING_MODE_FIT;
					mSetPlayerScaleButton.setImageResource(R.drawable.nemediacontroller_scale01);
					break;
				case VIDEO_SCALING_MODE_NONE:
					mVideoScalingMode = VIDEO_SCALING_MODE_NONE;
					mSetPlayerScaleButton.setImageResource(R.drawable.nemediacontroller_scale02);
					break;
				default:
					mVideoScalingMode = VIDEO_SCALING_MODE_NONE;
				};
	            mPlayer.setVideoScalingMode(mVideoScalingMode);
        	}
          
        	mSetPlayerScaleButton.requestFocus();
        	mSetPlayerScaleButton.setOnClickListener(mSetPlayerScaleListener);
        }
        
        mSnapshotButton = (ImageView) v.findViewById(R.id.snapShot);  //截图按钮
        if (mSnapshotButton != null) {
        	mSnapshotButton.requestFocus();
        	mSnapshotButton.setOnClickListener(mSnapShotListener);
        }
       
        mMuteButton = (ImageView) v.findViewById(R.id.video_player_mute);  //静音按钮
    	if (mMuteButton != null) {
    		if (mPlayer.isHardware() && mPlayer.isInBackground()) {
            	if (mute_flag) {
            		mMuteButton.setImageResource(R.drawable.nemediacontroller_mute01);
    				mPlayer.setMute(true);
            	}
            }
    		mMuteButton.setOnClickListener(mMuteListener);
    	}

        mProgress = (SeekBar) v.findViewById(R.id.mediacontroller_seekbar);  //进度条
        if (mProgress != null) {
            if (mProgress instanceof SeekBar) {
                SeekBar seeker = (SeekBar) mProgress;
                seeker.setOnSeekBarChangeListener(mSeekListener);
                seeker.setThumbOffset(1);
            }
            mProgress.setMax(1000);
        }
        

        mEndTime = (TextView) v.findViewById(R.id.mediacontroller_time_total); //总时长
        mCurrentTime = (TextView) v.findViewById(R.id.mediacontroller_time_current); //当前播放位置
        
//        if(mPlayer.getMediaType().equals("localaudio")) {
//        	mSetPlayerScaleButton.setVisibility(View.INVISIBLE); //播放音乐不需要设置显示模式，该按钮不显示
//        	mSnapshotButton.setVisibility(View.INVISIBLE);       //播放音乐不需要截图，该按钮不显示
//        	show();
//        }
        
//        if (mPlayer.isHardware()) {
//        	mSnapshotButton.setVisibility(View.INVISIBLE); //硬件解码不支持截图，该按钮不显示
//        }
    }
    //设置MediaPlayer使之与要绑定的控件绑定在一起其参数是一个MediaController.MediaPlayerControl 静态接口的对象，
    //(而VideoView是MediaController.MediaPlayerControl静态接口的子实现类，这就使得我们可以更好的控制我们的视频播放进度)
    public void setMediaPlayer(MediaPlayerControl player) {
        mPlayer = player;
        updatePausePlay();
    }

    /**
     * Control the action when the seekbar dragged by user
     * 
     * @param seekWhenDragging
     *            True the media will seek periodically
     */
    public void setInstantSeeking(boolean seekWhenDragging) {
        mInstantSeeking = seekWhenDragging;
    }

    //显示MediaController。默认显示3秒后自动隐藏。
    public void show() {
        show(sDefaultTimeout);
    }

    /**
     * Set the content of the file_name TextView
     * 设置视频文件名称。
     * 
     * @param name
     */
    public void setFileName(String name) {
        mTitle = name;
        if (mFileName != null)
            mFileName.setText(mTitle);
    }

    private void disableUnsupportedButtons() {
        try {
            if (mPauseButton != null && !mPlayer.canPause())
                mPauseButton.setEnabled(false);
        } catch (IncompatibleClassChangeError ex) {
        }
    }

    public void setAnimationStyle(int animationStyle) {
        mAnimStyle = animationStyle;
    }

    /**
     * Show the controller on screen. It will go away automatically after
     * 'timeout' milliseconds of inactivity.
     * 
     * @param timeout
     *            The timeout in milliseconds. Use 0 to show the controller
     *            until hide() is called.
     */
    @SuppressLint({ "InlinedApi", "NewApi" })
    public void show(int timeout) {
        if (!mShowing && mAnchor != null && mAnchor.getWindowToken() != null) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
                mAnchor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
            if (mPauseButton != null)
                mPauseButton.requestFocus();
            disableUnsupportedButtons();

            if (mFromXml) {
                setVisibility(View.VISIBLE);
            } else {
                int[] location = new int[2];

                mAnchor.getLocationOnScreen(location);
                Rect anchorRect = new Rect(location[0], location[1],
                        location[0] + mAnchor.getWidth(), location[1]
                                + mAnchor.getHeight());

                mWindow.setAnimationStyle(mAnimStyle);
                if (1==type){
                    mWindow.showAtLocation(mAnchor, Gravity.TOP, anchorRect.left, 355);
                }else{
                    mWindow.showAtLocation(mAnchor, Gravity.BOTTOM, anchorRect.left, 0);
                }

            }
            mShowing = true;
            if (mShownListener != null)
                mShownListener.onShown();
        }
        updatePausePlay();
        mHandler.sendEmptyMessage(SHOW_PROGRESS);

        if (timeout != 0) {
            mHandler.removeMessages(FADE_OUT);
            mHandler.sendMessageDelayed(mHandler.obtainMessage(FADE_OUT), timeout);
        }
    }

    public boolean isShowing() {
        return mShowing;
    }

    @SuppressLint({ "InlinedApi", "NewApi" })
    //隐藏MediaController。
    public void hide() {
        if (mAnchor == null)
            return;

        if (mShowing) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
                mAnchor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }
            try {
                mHandler.removeMessages(SHOW_PROGRESS);
                if (mFromXml)
                    setVisibility(View.GONE);
                else
                    mWindow.dismiss();
            } catch (IllegalArgumentException ex) {
            	Log.d(TAG, "MediaController already removed");
            }
            mShowing = false;
            if (mHiddenListener != null)
                mHiddenListener.onHidden();
        }
    }

    public interface OnShownListener {
        public void onShown();
    }

    private OnShownListener mShownListener;

    //注册一个回调函数，在MediaController显示后被调用。
    public void setOnShownListener(OnShownListener l) {
        mShownListener = l;
    }

    public interface OnHiddenListener {
        public void onHidden();
    }

    private OnHiddenListener mHiddenListener;

    //注册一个回调函数，在MediaController隐藏后被调用。
    public void setOnHiddenListener(OnHiddenListener l) {
        mHiddenListener = l;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            long pos;
            switch (msg.what) {
            case FADE_OUT:
                hide();
                break;
            case SHOW_PROGRESS:
                pos = setProgress();
                if (!mDragging && mShowing) {
                    msg = obtainMessage(SHOW_PROGRESS);
                    sendMessageDelayed(msg, 1000 - (pos % 1000));
                    updatePausePlay();
                }
                break;
            }
        }
    };

    private long setProgress() {
        if (mPlayer == null || mDragging)
            return 0;

        int position = mPlayer.getCurrentPosition();
        int duration = mPlayer.getDuration();
        if (mProgress != null) {
            if (duration > 0) {
                long pos = 1000L * position / duration;
                mProgress.setProgress((int) pos);
            }
            int percent = mPlayer.getBufferPercentage();
            mProgress.setSecondaryProgress(percent * 10);
        }

        mDuration = duration;

        if (mEndTime != null && mDuration > 0)
            mEndTime.setText(stringForTime(mDuration));
        else
        	mEndTime.setText("--:--:--");
        if (mCurrentTime != null)
            mCurrentTime.setText(stringForTime(position));

        return position;
    }

    private static String stringForTime(long position) {
        int totalSeconds = (int) ((position / 1000.0)+0.5);

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

//        if (hours > 0) {
            return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds).toString();
//        } else {
//            return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds).toString();
//        }
    }

    @SuppressLint("ClickableViewAccessibility")
	@Override
    public boolean onTouchEvent(MotionEvent event) {
        //show(sDefaultTimeout);
    	switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            show(0); // show until hide is called
            break;
        case MotionEvent.ACTION_UP:
            show(sDefaultTimeout); // start timeout
            break;
        case MotionEvent.ACTION_CANCEL:
            hide();
            break;
        default:
            break;
    }
        return true;
    }

    @Override
    public boolean onTrackballEvent(MotionEvent ev) {
        show(sDefaultTimeout);
        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (event.getRepeatCount() == 0
                && (keyCode == KeyEvent.KEYCODE_HEADSETHOOK
                        || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE || keyCode == KeyEvent.KEYCODE_SPACE)) {
            doPauseResume();
            show(sDefaultTimeout);
            if (mPauseButton != null)
                mPauseButton.requestFocus();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_STOP) {
            if (mPlayer.isPlaying()) {
                mPlayer.pause();
                updatePausePlay();
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK
                || keyCode == KeyEvent.KEYCODE_MENU) {
            hide();
            return true;
        } else {
            show(sDefaultTimeout);
        }
        return super.dispatchKeyEvent(event);
    }
    
    private View.OnClickListener mMuteListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			if (!mute_flag) {
				mMuteButton.setImageResource(R.drawable.nemediacontroller_mute01);
				mPlayer.setMute(true);
				mute_flag = true;
			}
			else {
				mMuteButton.setImageResource(R.drawable.nemediacontroller_mute02);
				mPlayer.setMute(false);
				mute_flag = false;
			}
		}
    };

	private View.OnClickListener mSetPlayerScaleListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			if(mIsFullScreen) {
				mVideoScalingMode = VIDEO_SCALING_MODE_NONE;
				mSetPlayerScaleButton.setImageResource(R.drawable.nemediacontroller_scale01);
				mIsFullScreen = false;
			}
			else {
				mVideoScalingMode = VIDEO_SCALING_MODE_FIT;
				mSetPlayerScaleButton.setImageResource(R.drawable.nemediacontroller_scale02);
				mIsFullScreen = true;
			}

            try {
            	mPlayer.setVideoScalingMode(mVideoScalingMode);
            } catch (NumberFormatException e) {

            }
		}
	};

	private View.OnClickListener mSnapShotListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			if(mPlayer.getMediaType().equals("localaudio") || mPlayer.isHardware()) {
				AlertDialog alertDialog;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                alertDialogBuilder.setTitle("注意");
                if (mPlayer.getMediaType().equals("localaudio"))
                	alertDialogBuilder.setMessage("音频播放不支持截图！");
                else if (mPlayer.isHardware())
                	alertDialogBuilder.setMessage("硬件解码不支持截图！");
                alertDialogBuilder.setCancelable(false)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ;
                        }
                    });
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                return;
			}

			mPlayer.getSnapshot();
		}
	};

    private View.OnClickListener mPauseListener = new View.OnClickListener() {
        public void onClick(View v) {
            doPauseResume();
            show(sDefaultTimeout);
        }
    };

    private void updatePausePlay() {
        if (mRoot == null || mPauseButton == null)
            return;

        if (mPlayer.isPlaying()) {
            mPauseButton.setImageResource(R.drawable.nemediacontroller_play);
		}
        else {
            mPauseButton.setImageResource(R.drawable.nemediacontroller_pause);
        }
    }

    private void doPauseResume() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            mPlayer.manualPause(true);
            mPaused = true;
        }
        else {
            mPlayer.start();
            mPlayer.manualPause(false);
            mPaused = false;
        }
        updatePausePlay();
    }

    private Runnable lastRunnable;
    private OnSeekBarChangeListener mSeekListener = new OnSeekBarChangeListener() {
        public void onStartTrackingTouch(SeekBar bar) {
        	show(3600000);
            mDragging = true;
            
            mHandler.removeMessages(SHOW_PROGRESS);
        }

        public void onProgressChanged(SeekBar bar, int progress, boolean fromuser) {
        	if (mPlayer.getMediaType().equals("livestream")) {
                return;
        	}
        	
            if (!fromuser)
                return;

            final long newposition = (mDuration * progress) / 1000;
            String time = stringForTime(newposition);
            if (mInstantSeeking) {
                mHandler.removeCallbacks(lastRunnable);
                lastRunnable = new Runnable() {
                    @Override
                    public void run() {
                        mPlayer.seekTo(newposition);
                    }
                };
                mHandler.postDelayed(lastRunnable, 200);
            }

            if (mCurrentTime != null)
                mCurrentTime.setText(time);
        }

        public void onStopTrackingTouch(SeekBar bar) {
        	if (mPlayer.getMediaType().equals("livestream")) {
        		AlertDialog alertDialog;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                alertDialogBuilder.setTitle("注意");
                alertDialogBuilder.setMessage("直播不支持seek操作"); 
                alertDialogBuilder.setCancelable(false)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ;
                        }
                    });
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                mProgress.setProgress(0);
                //return;
        	}
        	if (!mPlayer.getMediaType().equals("livestream")) {
	            if (!mInstantSeeking)
	                mPlayer.seekTo((mDuration * bar.getProgress()) / 1000);
        	}

            show(sDefaultTimeout);
            mHandler.removeMessages(SHOW_PROGRESS);
            mDragging = false;
            mHandler.sendEmptyMessageDelayed(SHOW_PROGRESS, 1000);
        }
    };

    @Override
    public void setEnabled(boolean enabled) {
        if (mPauseButton != null) {
            mPauseButton.setEnabled(enabled);
        }
        if (mProgress != null) {
            mProgress.setEnabled(enabled);
        }
        if (mSetPlayerScaleButton != null) {
        	mSetPlayerScaleButton.setEnabled(enabled);
        }
        if (mSnapshotButton != null) {
        	mSnapshotButton.setEnabled(enabled);
        }
        if (mMuteButton != null) {
        	mMuteButton.setEnabled(enabled);
        }
        	
        disableUnsupportedButtons();
        super.setEnabled(enabled);
    }

    public interface MediaPlayerControl {
        void start();

        void pause();

        int getDuration();

        int getCurrentPosition();

        void seekTo(long pos);

        boolean isPlaying();
        
        boolean isPaused();
        
        void manualPause(boolean paused);

        int getBufferPercentage();

        boolean canPause();

        boolean canSeekBackward();

        boolean canSeekForward();
        
        void getSnapshot();
        
        void setMute(boolean mute);
        
        void setVideoScalingMode(int videoScalingMode);
        
        String getMediaType();
        
        boolean isHardware();
        
        boolean isInBackground();
    }

}
