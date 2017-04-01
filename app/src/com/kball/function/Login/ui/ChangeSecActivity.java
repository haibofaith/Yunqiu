package com.kball.function.Login.ui;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.interfaceView.ChangePhoneView;
import com.kball.function.Login.interfaceView.ChangeSecView;
import com.kball.function.Login.presenter.ChangePhonePresenter;
import com.kball.function.Login.presenter.ChangeSecPresenter;
import com.kball.function.Mine.custom.TitleView;
import com.kball.function.home.ui.HomeActivity;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

/**
 * Created by user on 2017/3/13.
 */

public class ChangeSecActivity extends BaseActivity implements View.OnClickListener, ChangeSecView {
    private TitleView title_view;
    private EditText old_phone;//旧密码
    private EditText new_phone;//新密码
    private EditText code_edit;//确认密码
    private ChangeSecPresenter presenter;
    private TextView join_yunqiu_tv;

    @Override
    protected int getContentViewResId() {
        return R.layout.change_sec_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("修改密码");
        old_phone = (EditText) findViewById(R.id.old_phone);
        new_phone = (EditText) findViewById(R.id.new_phone);
        code_edit = (EditText) findViewById(R.id.code_edit);
        join_yunqiu_tv = (TextView) findViewById(R.id.join_yunqiu_tv);
    }

    @Override
    protected void initData() {
        presenter = new ChangeSecPresenter(this);
    }

    @Override
    protected void setListener() {
        join_yunqiu_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.join_yunqiu_tv:
                //1：修改密码，原始密码不可为null，2：创建密码，原始密码传null
                presenter.updatePassword(this, old_phone.getText().toString(), code_edit.getText().toString(), "1");
                break;
        }
    }

    @Override
    public void setChangeSecData(BaseBean result) {
        if ("1200".equals(result.getError_code())) {
            ToastAlone.show("修改密码成功，请重新登录");
            SPUtil.getInstance().putString(C.SP.USER_ID, "");
            SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN, "");
            startActivity(new Intent(this, HomeActivity.class));
        }
    }
}
