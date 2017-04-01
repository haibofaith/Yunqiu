package com.kball.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.kball.C;
import com.kball.util.KballActivityManager;
import com.kball.util.LoadingDialog;
import com.kball.util.LogUtil;
import com.kball.util.SPUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

public abstract class BaseActivity extends FragmentActivity  {
	protected static final int W_PX;
	protected static final int H_PX;
	protected String TAG;

	static {
		W_PX = SPUtil.getInstance().getInt(C.SP.SCREEN_WIDTH, 0);
		H_PX = SPUtil.getInstance().getInt(C.SP.SCREEN_HEIGHT, 0);
	}

	protected Activity mContext;
	protected ImageLoader imageLoader;
	protected SPUtil spUtil;
	private LoadingDialog loadingDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		 imageLoader = ImageLoader.getInstance();
		spUtil = SPUtil.getInstance();
		loadingDialog = new LoadingDialog(mContext);
		TAG = getClass().getSimpleName();
		KballActivityManager.getInstance().addActivity(mContext);

		setContentView(getContentViewResId());
		loadIntentData();

		initView();
		initData();
		setListener();
		LogUtil.d("WhichActivity",getClass().getName());
	}

	protected void loadIntentData() {
	}

	protected abstract int getContentViewResId();

	protected abstract void initView();

	protected abstract void initData();

	protected abstract void setListener();

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}
	@SuppressWarnings("unchecked")
	public <T extends View> T $(int ViewID){
		return (T)mContext.findViewById(ViewID);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onDestroy() {
		KballActivityManager.getInstance().removeActivity(mContext);
		super.onDestroy();
	}

	public void showLoading() {
		try {
			if (null != loadingDialog && !loadingDialog.isShowing()) {
				loadingDialog.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dismissLoading() {
		try {
			if (null != loadingDialog && loadingDialog.isShowing()) {
				loadingDialog.dismiss();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected <T extends View> T fViewById(int resId) {
		return (T) findViewById(resId);
	}

	protected void unbindDrawables(View view) {
		if (view == null) {
			return;
		}
		if (view.getBackground() != null) {
			view.getBackground().setCallback(null);
		}

		if (view instanceof ImageView) {
			ImageView imageView = (ImageView) view;
			imageView.setImageBitmap(null);
		} else if (view instanceof ViewGroup) {
			ViewGroup viewGroup = (ViewGroup) view;
			for (int i = 0; i < viewGroup.getChildCount(); i++) {
				unbindDrawables(viewGroup.getChildAt(i));
			}

			if (!(view instanceof AdapterView)) {
				viewGroup.removeAllViews();
			}
		}
	}

}
