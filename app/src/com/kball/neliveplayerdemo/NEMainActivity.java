package com.kball.neliveplayerdemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kball.R;

import java.util.ArrayList;
import java.util.List;


public class NEMainActivity extends Activity
{


	public final static String TAG = NEMainActivity.class.getSimpleName();
	private static final int REQUEST_CODE = 100;

	private TextView mMediaOption;    //显示播放选项
	private OnClickListener mOnClickEvent; //用于监听按钮事件
	private Button mBtnLiveStream;
	private Button mBtnVideoOnDemand;
	private ImageView mMediaTypeSelected;
	//private ImageView mMediaTypeUnselected;
	private EditText mEditURL; //用于输入网络流地址
	private Button mQRScan; // 二维码扫描
	private Button mBtnPlay;   //开始播放
	private RadioButton mHardware; //硬件解码
	private RadioButton mSoftware; //软件解码
	private TextView mHardwareReminder; //硬件解码提示语
	private String decodeType = "software";  //解码类型，默认软件解码
	private String mediaType = "livestream"; //媒体类型，默认网络直播

	private final int WRITE_PERMISSION_REQ_CODE = 100;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);


		setContentView(R.layout.activity_main);

		mMediaOption = (TextView)findViewById(R.id.mediaOption);
		mMediaOption.setGravity(Gravity.CENTER);

		mBtnLiveStream = (Button)findViewById(R.id.livestreamBtn);
		mBtnVideoOnDemand = (Button)findViewById(R.id.videoOnDemandBtn);

		mMediaTypeSelected = (ImageView)findViewById(R.id.mediaTypeSelected);

		mEditURL          = (EditText)findViewById(R.id.netVideoUrl);
		mQRScan           = (Button)findViewById(R.id.btnScan);
		mBtnPlay          = (Button)findViewById(R.id.play_button);

		mHardware         = (RadioButton)findViewById(R.id.hardware);
		mSoftware         = (RadioButton)findViewById(R.id.software);

		mSoftware.setButtonDrawable(R.drawable.decode_type_selected);
		mHardware.setButtonDrawable(R.drawable.decode_type_unselected);
		mHardwareReminder = (TextView)findViewById(R.id.hardware_reminder);

		DisplayMetrics dm = new DisplayMetrics(); //获取屏幕分辨率
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		final int screenW = dm.widthPixels;
		final int screenH = dm.heightPixels;

		final int buttonWidth = screenW / 2;
		mBtnLiveStream.setWidth(buttonWidth);
		mBtnVideoOnDemand.setWidth(buttonWidth);

		LayoutParams params = mMediaTypeSelected.getLayoutParams();
		params.width = screenW / 2;
		mMediaTypeSelected.setLayoutParams(params);

		mOnClickEvent = new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(NEMainActivity.this, NEVideoPlayerActivity.class);

				switch (v.getId()) {
					case R.id.livestreamBtn:
						mBtnLiveStream.setEnabled(false);
						mBtnVideoOnDemand.setEnabled(true);

						mMediaTypeSelected.setX((float) 0.0);

						mEditURL.setVisibility(View.VISIBLE);
						mEditURL.setHint("请输入直播流地址：URL");
						mHardware.setVisibility(View.VISIBLE);
						mSoftware.setVisibility(View.VISIBLE);
						mHardwareReminder.setVisibility(View.VISIBLE);
						mBtnPlay.setVisibility(View.VISIBLE);
						mediaType = "livestream";
						break;

					case R.id.videoOnDemandBtn:
						mBtnLiveStream.setEnabled(true);
						mBtnVideoOnDemand.setEnabled(false);

						mMediaTypeSelected.setX((float)buttonWidth);

						mEditURL.setVisibility(View.VISIBLE);
						mEditURL.setHint("请输入点播流地址：URL");
						mHardware.setVisibility(View.VISIBLE);
						mSoftware.setVisibility(View.VISIBLE);
						mHardwareReminder.setVisibility(View.VISIBLE);
						mBtnPlay.setVisibility(View.VISIBLE);
						mediaType = "videoondemand";
						break;


					case R.id.hardware:
						mSoftware.setButtonDrawable(R.drawable.decode_type_unselected);
						mHardware.setButtonDrawable(R.drawable.decode_type_selected);
						decodeType = "hardware";
						break;

					case R.id.software:
						mSoftware.setButtonDrawable(R.drawable.decode_type_selected);
						mHardware.setButtonDrawable(R.drawable.decode_type_unselected);
						decodeType = "software";
						break;

					case R.id.play_button:
						String url = mEditURL.getText().toString();
						Log.d(TAG, "url = "+ url);
						Log.d(TAG, "decode_type = "+ decodeType);

						if ((mediaType.equals("livestream") && url.isEmpty()) || (mediaType.equals("videoondemand") && url.isEmpty())) {
							AlertDialogBuild(0);
							break;
						}


						//把多个参数传给NEVideoPlayerActivity
						intent.putExtra("media_type", "software");
						intent.putExtra("decode_type", decodeType);
						intent.putExtra("videoPath", url);
						startActivity(intent);
						break;
				}
			}
		};

		mBtnLiveStream.setOnClickListener(mOnClickEvent);
		mBtnVideoOnDemand.setOnClickListener(mOnClickEvent);
		mHardware.setOnClickListener(mOnClickEvent);
		mSoftware.setOnClickListener(mOnClickEvent);
		mBtnPlay.setOnClickListener(mOnClickEvent);
		mQRScan.setOnClickListener(mOnClickEvent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data ==null || data.getExtras() == null || TextUtils.isEmpty(data.getExtras().getString("result"))) {
			return;
		}
		String result = data.getExtras().getString("result");
		if (mEditURL != null) {
			mEditURL.setText(result);
		}
	}

	public void AlertDialogBuild(int flag) //创建对话框
	{
		AlertDialog alertDialog;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("注意");
		if (flag == 0) {
			if (mediaType.equals("livestream")) {
				alertDialogBuilder.setMessage("请输入直播流地址");
			}
			else if (mediaType.equals("videoondemand")) {
				alertDialogBuilder.setMessage("请输入点播流地址");
			}
		}
		else if(flag == 1) {
			if (mediaType.equals("livestream")) {
				alertDialogBuilder.setMessage("请输入正确的直播流地址");
			}
			else if (mediaType.equals("videoondemand")) {
				alertDialogBuilder.setMessage("请输入正确的点播流地址");
			}
		}
		alertDialogBuilder.setCancelable(false)

				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						;
					}
				});
		alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	@Override
	public void onPause() {
		Log.d(TAG, "on pause");
		super.onPause();
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "on destroy");
		super.onDestroy();
	}

	@Override
	public void onRestart() {
		Log.d(TAG, "on restart");
		super.onRestart();
	}

	@Override
	public void onResume() {
		Log.d(TAG, "on resmue");
		super.onResume();
	}

	@Override
	public void onStart() {
		Log.d(TAG, "on start");
		super.onStart();
	}

}
