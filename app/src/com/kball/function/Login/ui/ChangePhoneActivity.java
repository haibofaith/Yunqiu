package com.kball.function.Login.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.interfaceView.ChangePhoneView;
import com.kball.function.Login.presenter.ChangePhonePresenter;
import com.kball.function.Mine.custom.TitleView;
import com.kball.util.ToastAlone;

/**
 * Created by user on 2017/3/13.
 */

public class ChangePhoneActivity extends BaseActivity implements View.OnClickListener, ChangePhoneView {
    private TitleView title_view;
    private EditText old_phone;
    private EditText new_phone;
    private EditText code_edit;
    private ImageView recive_code_img;
    private ChangePhonePresenter presenter;
    private TextView join_yunqiu_tv;

    @Override
    protected int getContentViewResId() {
        return R.layout.change_phone_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("更换手机绑定");
        old_phone = (EditText) findViewById(R.id.old_phone);
        new_phone = (EditText) findViewById(R.id.new_phone);
        code_edit = (EditText) findViewById(R.id.code_edit);
        recive_code_img = (ImageView) findViewById(R.id.recive_code_img);
        join_yunqiu_tv = (TextView) findViewById(R.id.join_yunqiu_tv);
    }

    @Override
    protected void initData() {
        presenter = new ChangePhonePresenter(this);
    }

    @Override
    protected void setListener() {
        recive_code_img.setOnClickListener(this);
        join_yunqiu_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recive_code_img:
                //6:修改手机号
                presenter.getVerifycode(this, "6", new_phone.getText().toString());
                break;
            case R.id.join_yunqiu_tv:
                //6:修改手机号
                presenter.updatePhone(this, old_phone.getText().toString(), new_phone.getText().toString(),code_edit.getText().toString());
                break;
        }
    }

    @Override
    public void setVerifycode(BaseBean result) {
        if ("1200".equals(result.getError_code())) {
            ToastAlone.show("验证码发送成功");
        }
    }

    @Override
    public void setUpdatePhoneData(BaseBean result) {
        if ("1200".equals(result.getError_code())) {
            ToastAlone.show("修改手机号成功");
        }
    }
}
