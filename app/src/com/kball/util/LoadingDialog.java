package com.kball.util;

import android.content.Context;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseDialog;


/**
 * 加载框
 */
public class LoadingDialog extends BaseDialog {
	private TextView tvShowText;

	public LoadingDialog(Context context) {
		super(context, R.layout.loading_dialog,
				R.style.kballTheme_Dialog_Loading);

	}

	@Override
	protected void initView() {
		tvShowText = (TextView) findViewById(R.id.tvShowText);

	}

	@Override
	protected void initData() {
		tvShowText.setText("加载中...");

	}

	@Override
	protected void setListener() {

	}

	public void setShowText(int resId) {
		tvShowText.setText(resId);
	}

	public void setShowText(String showText) {
		tvShowText.setText(showText);
	}

}
