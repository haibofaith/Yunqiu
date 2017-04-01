package com.kball.neliveplayerdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.kball.R;

//设置启动欢迎页
public class NEWelcomeActivity extends Activity {
	@SuppressWarnings("unused")
	private ImageView mNEWelcomeImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		mNEWelcomeImage = (ImageView) findViewById(R.id.welcome_image);
		
		new Handler().postDelayed(r, 2000); //设置2秒钟后切换到下个Activity
	}
	
	Runnable r = new Runnable() {
		public void run() {
			Intent intent = new Intent();
			intent.setClass(NEWelcomeActivity.this, NEMainActivity.class);
			startActivity(intent);
			finish();
		}
	};
}