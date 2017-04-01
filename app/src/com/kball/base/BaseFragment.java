package com.kball.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.kball.C;
import com.kball.util.LoadingDialog;
import com.kball.util.SPUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.nostra13.universalimageloader.core.ImageLoader;

public abstract class BaseFragment extends Fragment {
	protected AsyncHttpClient asyncHttpClient;
	protected Context mContext;
	protected SPUtil spUtil;
	private LoadingDialog loadingDialog;
	private BaseActivity activity;
	protected ImageLoader imageLoader;

	protected static final int W_PX;
	protected static final int H_PX;

	static {
		W_PX = SPUtil.getInstance().getInt(C.SP.SCREEN_WIDTH, 0);
		H_PX = SPUtil.getInstance().getInt(C.SP.SCREEN_HEIGHT, 0);
	}

	@Override
	public void onAttach(Activity activity) {
		if (activity instanceof BaseActivity) {
			this.activity = (BaseActivity) activity;
		}
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		mContext = getActivity();
		super.onCreate(savedInstanceState);
		asyncHttpClient = new AsyncHttpClient();
		spUtil = SPUtil.getInstance();
		imageLoader = ImageLoader.getInstance();
		if (null == activity) {
			loadingDialog = new LoadingDialog(getActivity());
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initView();
		initData();
		setListener();
	}

	protected abstract void initView();

	protected abstract void initData();

	protected abstract void setListener();

	protected <T extends View> T findViewById(int resId) {
		return (T) getView().findViewById(resId);
	}

	protected void showLoading() {
		if (null != activity) {
			activity.showLoading();
		} else {
			if (null != loadingDialog && !loadingDialog.isShowing()) {
				loadingDialog.show();
			}
		}
	}

	protected void dismissLoading() {
		if (null != activity) {
			activity.dismissLoading();
		} else {
			if (null != loadingDialog && loadingDialog.isShowing()) {
				loadingDialog.dismiss();
			}
		}
	}
}
