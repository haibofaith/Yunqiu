package com.kball.function.Mine.ui;

import android.webkit.WebView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Mine.custom.TitleView;

/**
 * Created by user on 2017/3/21.
 */

public class UserAgreementsActivity extends BaseActivity {
    TitleView titleView;
    private WebView web_view;
    @Override
    protected int getContentViewResId() {
        return R.layout.user_agreements_layout;
    }

    @Override
    protected void initView() {
        titleView = (TitleView) findViewById(R.id.title_view);
        titleView.setTitleText("用户协议");
        web_view = (WebView) findViewById(R.id.web_view);
    }

    @Override
    protected void initData() {
        String uri = "file:///android_asset/user_agreements.html";
        web_view.loadUrl(uri);
    }

    @Override
    protected void setListener() {

    }
}
