package com.kball.function.Login.ui;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Mine.custom.TitleView;

/**
 * Created by user on 2017/3/13.
 */

public class AccountManageActivity extends BaseActivity implements View.OnClickListener {
    private TitleView title_view;
    private RelativeLayout rlin_1, rlin_2, rlin_3;

    @Override
    protected int getContentViewResId() {
        return R.layout.account_manage_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("账号管理");
        rlin_1 = (RelativeLayout) findViewById(R.id.rlin_1);
        rlin_2 = (RelativeLayout) findViewById(R.id.rlin_2);
        rlin_3 = (RelativeLayout) findViewById(R.id.rlin_3);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        rlin_1.setOnClickListener(this);
        rlin_2.setOnClickListener(this);
        rlin_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlin_1:
                //变更手机号
                startActivity(new Intent(this, ChangePhoneActivity.class));
                break;
            case R.id.rlin_2:
                //密码修改
                //ChangeSecActivity
                startActivity(new Intent(this, ChangeSecActivity.class));
                break;
            case R.id.rlin_3:
                //账号绑定
                startActivity(new Intent(this,AccountBindActivity.class));
                break;
        }
    }
}
