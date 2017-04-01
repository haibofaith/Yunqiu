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
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Toast;

import com.netease.neliveplayer.NELivePlayer;
import com.netease.neliveplayer.NELivePlayer.OnBufferingUpdateListener;
import com.netease.neliveplayer.NELivePlayer.OnCompletionListener;
import com.netease.neliveplayer.NELivePlayer.OnErrorListener;
import com.netease.neliveplayer.NELivePlayer.OnInfoListener;
import com.netease.neliveplayer.NELivePlayer.OnPreparedListener;
import com.netease.neliveplayer.NELivePlayer.OnSeekCompleteListener;
import com.netease.neliveplayer.NELivePlayer.OnVideoParseErrorListener;
import com.netease.neliveplayer.NELivePlayer.OnVideoSizeChangedListener;
import com.netease.neliveplayer.NEMediaInfo;
import com.netease.neliveplayer.NEMediaPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Displays a video file. The VideoView class can load images from various
 * sources (such as resources or content providers), takes care of computing its
 * measurement from the video so that it can be used in any layout manager, and
 * provides various display options such as scaling and tinting.
 * 
 * VideoView also provide many wrapper methods for
 * {@link io.vov.vitamio.MediaPlayer}, such as {@link #getVideoWidth()},
 * {@link #setSubShown(boolean)}
 */
public class NEVideoView extends SurfaceView implements NEMediaController.MediaPlayerControl{
    private static final String TAG = NEVideoView.class.getSimpleName();

    private static final int IDLE = 0;
    private static final int INITIALIZED = 1;
    private static final int PREPARING = 2;
    private static final int PREPARED = 3;
    private static final int STARTED = 4;
    private static final int PAUSED = 5;
    private static final int STOPED = 6;
    private static final int PLAYBACKCOMPLETED = 7;
    private static final int END = 8;
    private static final int RESUME = 9;
    private static final int ERROR = -1;

    private int mCurrState = IDLE;
    private int mNextState = IDLE;

    private int mVideoScalingMode = VIDEO_SCALING_MODE_FIT;
    public static final int VIDEO_SCALING_MODE_NONE = 0;
    public static final int VIDEO_SCALING_MODE_FIT  = 1;
    public static final int VIDEO_SCALING_MODE_FILL = 2;
    public static final int VIDEO_SCALING_MODE_FULL = 3;


    private Uri mUri;
    private long mDuration = 0 ;
    private long mPlayableDuration = 0;
    private SurfaceHolder mSurfaceHolder = null;
    private NELivePlayer mMediaPlayer = null;
    private boolean       mIsPrepared;
    private int mVideoWidth;
    private int mVideoHeight;
    private int mPixelSarNum;
    private int mPixelSarDen;
    private int mSurfaceWidth;
    private int mSurfaceHeight;
    private NEMediaController mMediaController;
    private View mBuffer;
    private OnCompletionListener mOnCompletionListener;
    private OnPreparedListener mOnPreparedListener;
    private OnErrorListener mOnErrorListener;
    private OnSeekCompleteListener mOnSeekCompleteListener;
    private OnInfoListener mOnInfoListener;
    private OnBufferingUpdateListener mOnBufferingUpdateListener;
    private int mCurrentBufferPercentage;
    private long mSeekWhenPrepared;
    private int mBufferStrategy = 0; //直播低延时
    private boolean mHardwareDecoder = false;
    private boolean mPauseInBackground = false;
    private Context mContext;
    public static String mVersion = null;
    private String mMediaType;
    private boolean mMute = false;
    private boolean isBackground;
    private boolean manualPause = false;


    private String mLogPath = null;
    private int mLogLevel = 0;

    private NEVideoViewReceiver mReceiver; //接收资源释放成功的通知
    
    public NEVideoView(Context context) {
        super(context);
        mContext = context;
        initVideoView();
    }

    public NEVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        mContext = context;
        initVideoView();
    }

    public NEVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initVideoView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
        int height = getDefaultSize(mVideoHeight, heightMeasureSpec);
        if (mVideoWidth > 0 && mVideoHeight > 0) {
            if ( mVideoWidth * height  > width * mVideoHeight ) {
                //Log.i("@@@", "image too tall, correcting");
                //height = width * mVideoHeight / mVideoWidth;
            } else if ( mVideoWidth * height  < width * mVideoHeight ) {
                //Log.i("@@@", "image too wide, correcting");
                //width = height * mVideoWidth / mVideoHeight;
            } else {
                //Log.i("@@@", "aspect ratio is correct: " +
                //width+"/"+height+"="+
                //mVideoWidth+"/"+mVideoHeight);
            }
        }
        setMeasuredDimension(width, height);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setVideoScalingMode(int videoScalingMode) {
        LayoutParams layPara = getLayoutParams();
        int winWidth  = 0;
        int winHeight = 0;
        Rect rect = new Rect();
        this.getWindowVisibleDisplayFrame(rect);//获取状态栏高度
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay(); //获取屏幕分辨率
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) { //new
            DisplayMetrics metrics = new DisplayMetrics();
            display.getRealMetrics(metrics);
            winWidth  = metrics.widthPixels;
            winHeight = metrics.heightPixels - rect.top;
        }
        else { //old
			try {
				Method mRawWidth  = Display.class.getMethod("getRawWidth");
				Method mRawHeight = Display.class.getMethod("getRawHeight");
				winWidth  = (Integer) mRawWidth.invoke(display);
				winHeight = (Integer) mRawHeight.invoke(display) - rect.top;
			} catch (NoSuchMethodException e) {
				DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
				winWidth  = dm.widthPixels;
				winHeight = dm.heightPixels - rect.top;
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
        }

        float winRatio = (float) winWidth / winHeight;
        if (mVideoWidth > 0 && mVideoHeight > 0) {
            float aspectRatio = (float) (mVideoWidth) / mVideoHeight;
            if (mPixelSarNum > 0 && mPixelSarDen > 0)
				aspectRatio = aspectRatio * mPixelSarNum / mPixelSarDen;
            mSurfaceHeight = mVideoHeight;
            mSurfaceWidth = mVideoWidth;

            if (VIDEO_SCALING_MODE_NONE == videoScalingMode && mSurfaceWidth < winWidth && mSurfaceHeight < winHeight) {
				layPara.width = (int) (mSurfaceHeight * aspectRatio);
				layPara.height = mSurfaceHeight;
            } else if ( VIDEO_SCALING_MODE_FIT == videoScalingMode) { //拉伸
				if (winRatio < aspectRatio) {
					layPara.width  = winWidth;
					layPara.height = (int)(winWidth / aspectRatio);
				}
				else {
					layPara.width  = (int)(aspectRatio * winHeight);
					layPara.height = winHeight;
				}
			} else if (VIDEO_SCALING_MODE_FILL == videoScalingMode){ //全屏
				layPara.width  = winWidth;
                layPara.height = winHeight;
            } else if (VIDEO_SCALING_MODE_FULL == videoScalingMode) { //全屏
                if (winRatio < aspectRatio) {
                    layPara.width  = (int)(winHeight * aspectRatio);
                    layPara.height = winHeight;
                }
                else {
                    layPara.width  = winWidth;
                    layPara.height = (int)(winWidth / aspectRatio);
                }
            } else {
				if (winRatio < aspectRatio) {
					layPara.width  = (int)(aspectRatio * winHeight);
					layPara.height = winHeight;
				}
				else {
					layPara.width  = winWidth;
					layPara.height = (int)(winWidth / aspectRatio);
				}
            }
            setLayoutParams(layPara);
            getHolder().setFixedSize(mSurfaceWidth, mSurfaceHeight);
        }

        mVideoScalingMode = videoScalingMode;
    }

    private void initVideoView() {
        mVideoWidth = 0;
        mVideoHeight = 0;
        mPixelSarNum = 0;
        mPixelSarDen = 0;
        getHolder().addCallback(mSHCallback);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        registerBroadCast();
        mCurrState = IDLE;
        mNextState = IDLE;
    }

    public void setVideoPath(String path) { //设置视频文件路径
		isBackground = false; //指示是否在后台
        setVideoURI(Uri.parse(path));
    }

    public void setVideoURI(Uri uri) {
        mUri = uri;
        mSeekWhenPrepared = 0;
        openVideo();
        requestLayout();
        invalidate();
    }

    private void openVideo() {
        if (mUri == null || mSurfaceHolder == null) {
			// not ready for playback just yet, will try again later
            return;
        }

        // Tell the music playback service to pause
        // TODO: these constants need to be published somewhere in the framework 
        Intent i = new Intent("com.android.music.musicservicecommand");
        i.putExtra("command", "pause");
        mContext.sendBroadcast(i);

        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        try {

            NEMediaPlayer neMediaPlayer = null;
            if (mUri != null) {
				neMediaPlayer = new NEMediaPlayer();
            }
            mMediaPlayer = neMediaPlayer;
            getLogPath();
            mMediaPlayer.setLogPath(mLogLevel, mLogPath);
			mMediaPlayer.setBufferStrategy(mBufferStrategy);//设置缓冲策略，0为直播低延时，1为点播抗抖动
            mMediaPlayer.setHardwareDecoder(mHardwareDecoder);//设置是否开启硬件解码，0为软解，1为硬解
            mMediaPlayer.setOnPreparedListener(mPreparedListener);
            mIsPrepared = false;
            mMediaPlayer.setOnVideoSizeChangedListener(mSizeChangedListener);
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
            mMediaPlayer.setOnErrorListener(mErrorListener);
            mMediaPlayer.setOnBufferingUpdateListener(mBufferingUpdateListener);
            mMediaPlayer.setOnInfoListener(mInfoListener);
            mMediaPlayer.setOnSeekCompleteListener(mSeekCompleteListener);
            mMediaPlayer.setOnVideoParseErrorListener(mVideoParseErrorListener);
            if (mUri != null) {
                //设置播放地址，返回0正常，返回-1则说明地址非法，需要使用网易视频云官方生成的地址
                int ret = mMediaPlayer.setDataSource(mUri.toString());
                if (ret < 0) {
                    if (getWindowToken() != null  && mMediaType.equals("livestream")) {
                        new AlertDialog.Builder(mContext)
                                .setTitle("error")
                                .setMessage("地址非法，请输入网易视频云官方地址！")
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                    	/* If we get here, there is no onError listener, so
                                         * at least inform them that the video is over.
                                         */
                                                if (mOnCompletionListener != null)
                                                    mOnCompletionListener.onCompletion(mMediaPlayer);
                                            }
                                        })
                                .setCancelable(false)
                                .show();
                    }
                    release_resource();
                    return;
                }
                mCurrState = INITIALIZED;
                mNextState = PREPARING;
            }
            mMediaPlayer.setPlaybackTimeout(30000);
            mMediaPlayer.setDisplay(mSurfaceHolder);
            mMediaPlayer.setScreenOnWhilePlaying(true);
            mMediaPlayer.prepareAsync(mContext); //初始化视频文件
            mCurrState = PREPARING;
            attachMediaController();
        } catch (IOException ex) {
			Log.e(TAG, "Unable to open content: " + mUri, ex);
            mErrorListener.onError(mMediaPlayer, -1, 0);
            return;
        } catch (IllegalArgumentException ex) {
			Log.e(TAG, "Unable to open content: " + mUri, ex);
            mErrorListener.onError(mMediaPlayer, -1, 0);
            return;
        }
    }

    public void setMediaController(NEMediaController controller) {
        if (mMediaController != null) {
            mMediaController.hide();
        }
        mMediaController = controller;
        attachMediaController();
    }

    public void setBufferingIndicator(View buffer) {
        if (mBuffer != null)
 			mBuffer.setVisibility(View.GONE);
        mBuffer = buffer;
    }

    private void attachMediaController() {
        if (mMediaPlayer != null && mMediaController != null) {
            mMediaController.setMediaPlayer(this);
            View anchorView = this.getParent() instanceof View ?
            		(View) this.getParent() : this;
            mMediaController.setAnchorView(anchorView);
            mMediaController.setEnabled(mIsPrepared);

            if (mUri != null) {
                List<String> paths = mUri.getPathSegments();
                String name = paths == null || paths.isEmpty() ? "null" : paths
                        .get(paths.size() - 1);
                mMediaController.setFileName(name);
            }
        }
    }

    OnVideoSizeChangedListener mSizeChangedListener = new OnVideoSizeChangedListener() {
        public void onVideoSizeChanged(NELivePlayer mp, int width, int height,
                                       int sarNum, int sarDen) {
        	Log.d(TAG, "onVideoSizeChanged: " + width + "x"+ height);
            mVideoWidth = mp.getVideoWidth();
            mVideoHeight = mp.getVideoHeight();
            mPixelSarNum = sarNum;
            mPixelSarDen = sarDen;
            if (mVideoWidth != 0 && mVideoHeight != 0)
                setVideoScalingMode(mVideoScalingMode);
        }
    };

    OnPreparedListener mPreparedListener = new OnPreparedListener() {
        public void onPrepared(NELivePlayer mp) {
        	Log.i(TAG, "onPrepared");
        	mCurrState = PREPARED;
        	mNextState = STARTED;
        	// briefly show the mediacontroller
        	mIsPrepared = true;

            if (mOnPreparedListener != null) {
                mOnPreparedListener.onPrepared(mMediaPlayer);
            }
            if (mMediaController != null) {
                mMediaController.setEnabled(true);
            }
            mVideoWidth = mp.getVideoWidth();
            mVideoHeight = mp.getVideoHeight();

            if (mSeekWhenPrepared != 0)
                seekTo(mSeekWhenPrepared);
            if (mVideoWidth != 0 && mVideoHeight != 0) {
            	setVideoScalingMode(mVideoScalingMode);
                if (mSurfaceWidth == mVideoWidth && mSurfaceHeight == mVideoHeight) {
                    if (mNextState == STARTED) {
                    	if (!isPaused()) {
                    		start();
                    	}
                        if (mMediaController != null)
                            mMediaController.show();
                    } else if (!isPlaying() && (mSeekWhenPrepared != 0 || getCurrentPosition() > 0)) {
                        if (mMediaController != null)
                            mMediaController.show(0);
                    }
                }
            } else if (mNextState == STARTED) {
            	if (!isPaused()) {
            		start();
            	}else {
            		pause();
            	}
            }
        }
    };

    private OnCompletionListener mCompletionListener = new OnCompletionListener() {
        public void onCompletion(NELivePlayer mp) {
        	Log.d(TAG, "onCompletion");
        	mCurrState = PLAYBACKCOMPLETED;
            if (mMediaController != null)
                mMediaController.hide();
            if (mOnCompletionListener != null)
                mOnCompletionListener.onCompletion(mMediaPlayer);
            
            if (getWindowToken() != null  && mMediaType.equals("livestream")) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Completed!")
                        .setMessage("播放结束！")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                    	/* If we get here, there is no onError listener, so
                                         * at least inform them that the video is over.
                                         */
                                        if (mOnCompletionListener != null)
                                            mOnCompletionListener.onCompletion(mMediaPlayer);
                                    }
                                })
                        .setCancelable(false)
                        .show();
            }
        }
    };

    private OnErrorListener mErrorListener = new OnErrorListener() {
        public boolean onError(NELivePlayer mp, int a, int b) {
        	Log.d(TAG, "Error: " + a + "," + b);
        	mCurrState = ERROR;
            if (mMediaController != null) {
                mMediaController.hide();
            }

            /* If an error handler has been supplied, use it and finish. */
            if (mOnErrorListener != null) {
                if (mOnErrorListener.onError(mMediaPlayer, a, b)) {
                    return true;
                }
            }

            /* Otherwise, pop up an error dialog so the user knows that
             * something bad has happened. Only try and pop up the dialog
             * if we're attached to a window. When we're going away and no
             * longer have a window, don't bother showing the user an error.
             */
            if (getWindowToken() != null) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Error")
                        .setMessage("播放拉流失败")
                        .setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                    	/* If we get here, there is no onError listener, so
                                         * at least inform them that the video is over.
                                         */
                                        if (mOnCompletionListener != null)
                                            mOnCompletionListener.onCompletion(mMediaPlayer);
                                    }
                                })
                        .setCancelable(false)
                        .show();
            }
            return true;
        }
    };

    private OnBufferingUpdateListener mBufferingUpdateListener = new OnBufferingUpdateListener() {
        public void onBufferingUpdate(NELivePlayer mp, int percent) {
            mCurrentBufferPercentage = percent;
            if (mOnBufferingUpdateListener != null)
                mOnBufferingUpdateListener.onBufferingUpdate(mp, percent);
        }
    };

    private OnInfoListener mInfoListener = new OnInfoListener() {
        @Override
        public boolean onInfo(NELivePlayer mp, int what, int extra) {
			Log.d(TAG, "onInfo: " + what + ", " + extra);
            if (mOnInfoListener != null) {
                mOnInfoListener.onInfo(mp, what, extra);
            }

            if (mMediaPlayer != null) {
                if (what == NELivePlayer.NELP_BUFFERING_START) {
					Log.d(TAG, "onInfo: NELP_BUFFERING_START");
                    if (mBuffer != null)
						mBuffer.setVisibility(View.VISIBLE);
                } else if (what == NELivePlayer.NELP_BUFFERING_END) {
					Log.d(TAG, "onInfo: NELP_BUFFERING_END");
                    if (mBuffer != null)
						mBuffer.setVisibility(View.GONE);
                } else if (what == NELivePlayer.NELP_FIRST_VIDEO_RENDERED) {
                    Log.d(TAG, "onInfo: NELP_FIRST_VIDEO_RENDERED");
                } else if (what == NELivePlayer.NELP_FIRST_AUDIO_RENDERED) {
                    Log.d(TAG, "onInfo: NELP_FIRST_AUDIO_RENDERED");
                }
            }

            return true;
        }
    };

    private OnSeekCompleteListener mSeekCompleteListener = new OnSeekCompleteListener() {
        @Override
        public void onSeekComplete(NELivePlayer mp) {
			Log.d(TAG, "onSeekComplete");
            if (mOnSeekCompleteListener != null)
                mOnSeekCompleteListener.onSeekComplete(mp);
        }
    };

    private NELivePlayer.OnVideoParseErrorListener mVideoParseErrorListener = new OnVideoParseErrorListener() {
        public void onVideoParseError(NELivePlayer mp) {
            Log.i(TAG, "onVideoParseError");
        }
    };

    /**
     * Register a callback to be invoked when the media file
     * is loaded and ready to go.
     *
     * @param l The callback that will be run
     */
    public void setOnPreparedListener(OnPreparedListener l) {
        mOnPreparedListener = l;
    }

    /**
     * Register a callback to be invoked when the end of a media file
     * has been reached during playback.
     *
     * @param l The callback that will be run
     */
    public void setOnCompletionListener(OnCompletionListener l) {
        mOnCompletionListener = l;
    }

    /**
     * Register a callback to be invoked when an error occurs
     * during playback or setup.  If no listener is specified,
     * or if the listener returned false, VideoView will inform
     * the user of any errors.
     *
     * @param l The callback that will be run
     */
    public void setOnErrorListener(OnErrorListener l) {
        mOnErrorListener = l;
    }

    public void setOnBufferingUpdateListener(OnBufferingUpdateListener l) {
        mOnBufferingUpdateListener = l;
    }

    public void setOnSeekCompleteListener(OnSeekCompleteListener l) {
        mOnSeekCompleteListener = l;
    }

    public void setOnInfoListener(OnInfoListener l) {
        mOnInfoListener = l;
    }

    SurfaceHolder.Callback mSHCallback = new SurfaceHolder.Callback() {
        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
            mSurfaceHolder = holder;
            if (mMediaPlayer != null) {
                mMediaPlayer.setDisplay(mSurfaceHolder);
            }

            mSurfaceWidth = w;
            mSurfaceHeight = h;
            if (mMediaPlayer != null && mIsPrepared && mVideoWidth == w && mVideoHeight == h) {
                if (mSeekWhenPrepared != 0)
                    seekTo(mSeekWhenPrepared);
                if (!isPaused()) {
                	start();
                }
                if (mMediaController != null) {
                    mMediaController.show();
                }
            }
        }

        public void surfaceCreated(SurfaceHolder holder) {
            mSurfaceHolder = holder;

            if (mNextState != RESUME && !isBackground) {
        		openVideo();
        	} 
        	else {
        		if (mHardwareDecoder) {
        			openVideo();
        			isBackground = false; //不在后台
        		}
        		else if (mPauseInBackground) {
        			//mMediaPlayer.setDisplay(mSurfaceHolder);
        			if (!isPaused())
        				start();
        			isBackground = false; //不在后台
        		}
        	}
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            mSurfaceHolder = null;
            if (mMediaController != null) mMediaController.hide();
            if (mMediaPlayer != null) {
            	if(mHardwareDecoder) {
            		mSeekWhenPrepared = mMediaPlayer.getCurrentPosition();
            		if (mMediaPlayer != null) {
                        mMediaPlayer.reset();
                        mMediaPlayer.release();
                        mMediaPlayer = null;
                        mCurrState = IDLE;
                    }
            		isBackground = true;
            	}
            	else if (!mPauseInBackground) {
            		mMediaPlayer.setDisplay(null);
            		isBackground = true;
            	}
            	else {
            		pause();
            		isBackground = true;
            	}
            	
            	mNextState = RESUME;   
            }
        }
    };


    @SuppressLint("ClickableViewAccessibility")
	@Override
    public boolean onTouchEvent(MotionEvent ev) {
    	if (mIsPrepared && mMediaPlayer != null && mMediaController != null)
            toggleMediaControlsVisiblity();
        return false;
    }

    @Override
    public boolean onTrackballEvent(MotionEvent ev) {
    	if (mIsPrepared && mMediaPlayer != null && mMediaController != null)
            toggleMediaControlsVisiblity();
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mIsPrepared && 
        		keyCode != KeyEvent.KEYCODE_BACK
                && keyCode != KeyEvent.KEYCODE_VOLUME_UP
                && keyCode != KeyEvent.KEYCODE_VOLUME_DOWN
                && keyCode != KeyEvent.KEYCODE_MENU
                && keyCode != KeyEvent.KEYCODE_CALL
                && keyCode != KeyEvent.KEYCODE_ENDCALL
                && mMediaPlayer != null
                && mMediaController != null) {
            if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK
                    || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE
                    || keyCode == KeyEvent.KEYCODE_SPACE) {
                if (mMediaPlayer.isPlaying()) {
                    pause();
                    mMediaController.show();
                } else {
                	if (!isPaused()) {
                    	start();
                    }
                    mMediaController.hide();
                }
                return true;
            }
            else {
                toggleMediaControlsVisiblity();
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    private void toggleMediaControlsVisiblity() {
        if (mMediaController.isShowing()) {
            mMediaController.hide();
        } else {
            mMediaController.show();
        }
    }
    
    public void MediaControlsVisibity(boolean showing) {
    	if (showing) {
    		mMediaController.show();
    	}
    	else {
    		mMediaController.hide();
    	}
    }
    
    @Override
    public void start() {
    	if (mMediaPlayer != null && mIsPrepared) {
            mMediaPlayer.start();
            mCurrState = STARTED;
        }
    	mNextState = STARTED;
    }

    @Override
    public void pause() {
    	if (mMediaPlayer != null && mIsPrepared) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
                mCurrState = PAUSED;
            }
        }
        mNextState = PAUSED;
    }

    @Override
    public int getDuration() {
    	if (mMediaPlayer != null && mIsPrepared) {
            if (mDuration > 0)
                return (int) mDuration;
            mDuration = mMediaPlayer.getDuration();
            return (int) mDuration;
        }
        
        return -1;
    }
    
    public int getPlayableDuration() {
    	if (mMediaPlayer != null && mIsPrepared) {
    		if (mPlayableDuration > 0)
    			return (int) mPlayableDuration;
    		mPlayableDuration = mMediaPlayer.getPlayableDuration();
    		return (int) mPlayableDuration;
    	}
    	
        return -1;
    }

    @Override
    public int getCurrentPosition() {
    	if (mMediaPlayer != null && mIsPrepared) {
    		return (int)mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override
    public void seekTo(long msec) {
    	if (mMediaPlayer != null && mIsPrepared) {
            mMediaPlayer.seekTo(msec);
            mSeekWhenPrepared = 0;
        } else {
            mSeekWhenPrepared = msec;
        }
    }

    @Override
    public boolean isPlaying() {
    	if (mMediaPlayer != null && mIsPrepared) {
    		return mMediaPlayer.isPlaying();
    	}
    	return false;
    }
    
    public void manualPause(boolean paused) {
    	manualPause = paused;
    }
    
    public boolean isPaused() {
    	//return (mCurrentState == PLAY_STATE_PAUSED) ? true : false;
    	return manualPause;
    }

    @Override
    public int getBufferPercentage() {
        if (mMediaPlayer != null)
            return mCurrentBufferPercentage;
        return 0;
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }
    
    public void setMediaType(String MediaType) {
    	mMediaType = MediaType;
    }
    
    public String getMediaType() {
    	return mMediaType;
    }
    
    public void setBufferStrategy(int bufferStrategy) {
    	mBufferStrategy = bufferStrategy;
    }
    
    public boolean isHardware() {
    	return mHardwareDecoder;
    }
    
    public void setHardwareDecoder(boolean enabled) {
    	mHardwareDecoder = enabled;
    	if (mHardwareDecoder) {
    		mPauseInBackground = true;
    	}
    }
    
    public boolean isInBackground() {
    	return isBackground;
    }
    
    public void setPauseInBackground(boolean enabled) {
    	mPauseInBackground = enabled;
    	
    	if (mHardwareDecoder) {
    		mPauseInBackground = true;
    	}
    }
    
	public void setMute(boolean mute) {
		if (mMediaPlayer == null)
    		return;
    	mMute = mute;
    	mMediaPlayer.setMute(mMute);
    }
	
    @SuppressLint("SdCardPath")
	public void getSnapshot() {
    	NEMediaInfo mediaInfo = mMediaPlayer.getMediaInfo();

    	if (mediaInfo.mVideoDecoderMode.equals("MediaCodec"))
    	{
    		Log.d(TAG, "================= hardware unsupport snapshot ==============");
    	}
    	else
    	{
	    	Bitmap bitmap = Bitmap.createBitmap(mVideoWidth, mVideoHeight, Config.ARGB_8888);
	    	//Bitmap bitmap = null;
	    	mMediaPlayer.getSnapshot(bitmap);
	    	String picName = "/sdcard/NESnapshot.jpg";
	    	File f = new File(picName);
	    	try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    	FileOutputStream fOut = null;
			try {
				fOut = new FileOutputStream(f);
				if (picName.substring(picName.lastIndexOf(".") + 1, picName.length()).equals("jpg")) {
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
	   			}
	   			else if (picName.substring(picName.lastIndexOf(".") + 1, picName.length()).equals("png")) {
	   				bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
	   			}
				fOut.flush();
				fOut.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}			
			
			Toast.makeText(mContext, "截图成功", Toast.LENGTH_SHORT).show();
    	}
    }
    
    public String getVersion() {
    	if (mMediaPlayer == null)
    		return null;
    	return mMediaPlayer.getVersion();
    }
	
    public void release_resource() {
    	if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
            mCurrState = IDLE;
        }
    }
    
    public void setLogLevel(int logLevel) {
        mLogLevel = logLevel;
    	if (mMediaPlayer == null)
            return;
    	mMediaPlayer.setLogLevel(logLevel);
    }

    //获取日志文件路径
    public void getLogPath()
    {
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                mLogPath = Environment.getExternalStorageDirectory() + "/log/";
            }
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
    }

    // 以下用于接收资源释放成功的消息

    /**
     * 注册监听器
     */
    private void registerBroadCast(){
        unRegisterBroadCast();
        mReceiver = new NEVideoViewReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(NEMediaPlayer.NELP_RELEASE_SUCCESS);
        mContext.registerReceiver(mReceiver, filter);
    }

    /**
     * 反注册监听器
     */
    private void unRegisterBroadCast(){
        if(mReceiver != null){
            mContext.unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }

    /**
     * 资源释放成功通知的消息接收器类
     */
    private class NEVideoViewReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(NEMediaPlayer.NELP_RELEASE_SUCCESS)){
                Log.i(TAG, NEMediaPlayer.NELP_RELEASE_SUCCESS);
                unRegisterBroadCast(); // 接收到消息后反注册监听器
            }
        }
    }
}
