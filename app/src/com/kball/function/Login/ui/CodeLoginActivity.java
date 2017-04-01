package com.kball.function.Login.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.function.Login.interfaceView.CodeLoginView;
import com.kball.function.Login.presenter.CodeLoginPresenter;
import com.kball.function.Mine.custom.TitleView;
import com.kball.function.home.ui.HomeActivity;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import static com.kball.C.SP.USER_INFO;

/**
 * Created by user on 2017/2/21.
 */

public class CodeLoginActivity extends BaseActivity implements View.OnClickListener, CodeLoginView {
    private TitleView title_view;
    private LinearLayout pass_login_rlin;
    private ImageView weixin_login;
    private ImageView qq_login;
    private TextView join_yunqiu_tv;
    private TextView register_tv;
    private EditText phone_edit;
    private ImageView recive_code_img;
    private EditText code_edit;
    private CodeLoginPresenter presenter;

    private boolean flag = false;
    private static int countFlag = 60;
    private TextView djs_tv;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 100:
                    djs_tv.setText(countFlag+"秒");
                    countFlag--;
                    break;
                case 101:
                    recive_code_img.setVisibility(View.VISIBLE);
                    djs_tv.setVisibility(View.GONE);
                    countFlag = 60;
                    break;
                default:
                    break;
            }
            return true;
        }
    });
    @Override
    protected int getContentViewResId() {
        return R.layout.code_login_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        pass_login_rlin = (LinearLayout) findViewById(R.id.pass_login_rlin);
        weixin_login = (ImageView) findViewById(R.id.weixin_login);
        qq_login = (ImageView) findViewById(R.id.qq_login);
        join_yunqiu_tv = (TextView) findViewById(R.id.join_yunqiu_tv);
        register_tv = (TextView) findViewById(R.id.register_tv);
        title_view.setTitleText("");
        title_view.setLeftButtonText("关闭");
        title_view.setLeftButtonImgGone();
        phone_edit = (EditText) findViewById(R.id.phone_edit);
        recive_code_img = (ImageView) findViewById(R.id.recive_code_img);
        code_edit = (EditText) findViewById(R.id.code_edit);

        djs_tv = (TextView)findViewById(R.id.djs_tv);
    }

    @Override
    protected void initData() {
        presenter = new CodeLoginPresenter(this);
    }

    @Override
    protected void setListener() {
        pass_login_rlin.setOnClickListener(this);
        weixin_login.setOnClickListener(this);
        qq_login.setOnClickListener(this);
        join_yunqiu_tv.setOnClickListener(this);
        register_tv.setOnClickListener(this);
        recive_code_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pass_login_rlin:
                startActivity(new Intent(this, PassLoginActivity.class));
                finish();
                break;
            case R.id.weixin_login:
                startActivity(new Intent(this, BindPhoneActivity.class));
                break;
            case R.id.qq_login:
                startActivity(new Intent(this, BindPhoneActivity.class));
                break;
            case R.id.join_yunqiu_tv:
                if (TextUtils.isEmpty(phone_edit.getText().toString())) {
                    ToastAlone.show("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(code_edit.getText().toString())) {
                    ToastAlone.show("请输入验证码");
                    return;
                }
                presenter.login(this, "5", phone_edit.getText().toString(), code_edit.getText().toString());
                break;
            case R.id.register_tv:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.recive_code_img:
                if (TextUtils.isEmpty(phone_edit.getText().toString())) {
                    ToastAlone.show("请输入手机号");
                    return;
                }
                presenter.getVerifycode(this, "5", phone_edit.getText().toString());
                flag = true;
                recive_code_img.setVisibility(View.GONE);
                djs_tv.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (flag){
                            mHandler.sendEmptyMessage(100);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (countFlag == 0){
                                flag = false;
                                mHandler.sendEmptyMessage(101);
                            }
                        }
                    }
                }).start();
                break;
        }
    }

    @Override
    public void setVerifycode(BaseBean result) {
        if ("1200".equals(result.getError_code())) {
            ToastAlone.show(result.getMsg());
        }
    }

    @Override
    public void setObjData(BaseBean<UserRegisterBean> result) {
        if ("1200".equals(result.getError_code())) {
            ToastAlone.show(result.getMsg());
            Intent intent = new Intent(this, HomeActivity.class);
            if (result != null && result.getData() != null && result.getData().getUser_id() != null) {
                SPUtil.getInstance().putString(C.SP.USER_ID, result.getData().getUser_id());
                SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN, result.getData().getAccess_token());
                SPUtil.getInstance().putString(C.SP.EXPIRES_IN, result.getData().getExpires_in());
                SPUtil.getInstance().putString(C.SP.REFRESH_TOKEN, result.getData().getRefresh_token());
                SPUtil.getInstance().putString(C.SP.BOUND_STATUS, result.getData().getBound_status());
                SPUtil.getInstance().putString(C.SP.OPENID, result.getData().getOpenid());
                startActivity(intent);
            }

        }
    }
}
