package com.kball.function.Login.ui;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Mine.custom.TitleView;
import com.kball.function.Mine.ui.AboutUsActivity;
import com.kball.util.ToastAlone;

/**
 * Created by xiaole.wang on 17/3/1.
 */

public class UserSettingAct  extends BaseActivity implements View.OnClickListener {
    private TitleView title_view;
    private TextView exit_btn;
    private RelativeLayout update_info,user_manager,version_manager,about_our;
    @Override
    protected int getContentViewResId() {
        return R.layout.user_setting_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView)findViewById(R.id.title_view);
        exit_btn = (TextView)findViewById(R.id.exit_btn);
        update_info = (RelativeLayout)findViewById(R.id.update_info);
        user_manager = (RelativeLayout)findViewById(R.id.user_manager);
        version_manager = (RelativeLayout)findViewById(R.id.version_manager);
        about_our = (RelativeLayout)findViewById(R.id.about_our);

    }

    @Override
    protected void initData() {
        title_view.setTitleText("设置");

    }

    @Override
    protected void setListener() {
        exit_btn.setOnClickListener(this);
        update_info.setOnClickListener(this);
        user_manager.setOnClickListener(this);
        version_manager.setOnClickListener(this);
        about_our.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exit_btn:
                spUtil.putString(C.SP.USER_ID,"");
                setResult(11);
                finish();
                break;
            case R.id.update_info:
                startActivity(new Intent().setClass(mContext,UpdateInfoAct.class));
                break;
            case R.id.user_manager:
                startActivity(new Intent().setClass(mContext,AccountManageActivity.class));
                break;
            case R.id.version_manager:
                ToastAlone.show("功能正在开发中");
                break;
            case R.id.about_our:
                startActivity(new Intent().setClass(mContext, AboutUsActivity.class));
                break;

        }

    }
}
