package com.kball.function.Discovery.ui;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Mine.custom.TitleView;

/**
 * Created by user on 2017/2/21.
 */

public class LaunchFightActivity extends BaseActivity implements View.OnClickListener {
    private TitleView title_view;
    private TextView create_tv;//创建

    @Override
    protected int getContentViewResId() {
        return R.layout.launch_fight_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("发起约战");
        create_tv = (TextView) findViewById(R.id.create_tv);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        create_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_tv:
//                startActivity(new Intent(this, DisMatchActivity.class));
                break;
        }
    }
}
