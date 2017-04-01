package com.kball.function.Discovery.ui;

import android.view.View;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Mine.custom.TitleView;

/**
 * Created by user on 2017/2/22.
 */

public class DisSelectActivity extends BaseActivity implements View.OnClickListener {
    private TitleView title_view;

    @Override
    protected int getContentViewResId() {
        return R.layout.dis_select_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("筛选");
        title_view.setRightButtonVis();
        title_view.setRightButtonText("确定");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        title_view.setRightButtonListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
