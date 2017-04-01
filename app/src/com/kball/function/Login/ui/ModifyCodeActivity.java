package com.kball.function.Login.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.interfaceView.ModifyView;
import com.kball.function.Login.presenter.ModifyPresenter;
import com.kball.function.Mine.custom.TitleView;
import com.kball.util.ToastAlone;

import static com.kball.C.SP.USER_INFO;

/**
 * Created by user on 2017/2/21.
 */

public class ModifyCodeActivity extends BaseActivity implements View.OnClickListener, ModifyView {
    private TitleView title_view;
    private TextView certain_tv;//完成按钮
    private EditText phone_edit;
    private EditText code_edit;
    private ImageView recive_code_img;
    private EditText sec_edit;
    private EditText certain_sec_edit;
    private ModifyPresenter modifyPresenter;

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
        return R.layout.modify_code_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        certain_tv = (TextView) findViewById(R.id.certain_tv);
        title_view.setTitleText("修改密码");
        phone_edit = (EditText) findViewById(R.id.phone_edit);
        code_edit = (EditText) findViewById(R.id.code_edit);
        sec_edit = (EditText) findViewById(R.id.sec_edit);
        certain_sec_edit = (EditText) findViewById(R.id.certain_sec_edit);

        recive_code_img = (ImageView) findViewById(R.id.recive_code_img);
        djs_tv = (TextView)findViewById(R.id.djs_tv);
    }

    @Override
    protected void initData() {
        modifyPresenter = new ModifyPresenter(this);
    }

    @Override
    protected void setListener() {
        certain_tv.setOnClickListener(this);
        recive_code_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.certain_tv:
                if (phone_edit.getText().toString() == null || "".equals(phone_edit.getText().toString())) {
                    ToastAlone.show("请输入手机号");
                    return;
                }
                if (code_edit.getText().toString() == null || "".equals(code_edit.getText().toString())) {
                    ToastAlone.show("请输入验证码");
                    return;
                }
                if (sec_edit.getText().toString() == null || "".equals(sec_edit.getText().toString())) {
                    ToastAlone.show("请输入密码");
                    return;
                }
                if (certain_sec_edit.getText().toString() == null || "".equals(certain_sec_edit.getText().toString())) {
                    ToastAlone.show("请再次输入密码");
                    return;
                }
                if (!certain_sec_edit.getText().toString().equals(sec_edit.getText().toString())) {
                    ToastAlone.show("两次输入密码不一致");
                    return;
                }
                modifyPresenter.updatePasswordByPhone(this,phone_edit.getText().toString(),code_edit.getText().toString(),sec_edit.getText().toString());
                break;
            case R.id.recive_code_img:
                if (phone_edit.getText().toString() == null || "".equals(phone_edit.getText().toString())) {
                    ToastAlone.show("请输入手机号");
                    return;
                }
                modifyPresenter.getVerifycode(this, "4", phone_edit.getText().toString());
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
        if (result == null) {
            return;
        }
        if ("1200".equals(result.getError_code())) {
            ToastAlone.show("验证码发送成功");
        }
    }

    @Override
    public void setObjData(BaseBean result) {
        if (result == null) {
            return;
        }
        if ("1200".equals(result.getError_code())) {
            ToastAlone.show("密码修改成功");
            SharedPreferences preferences = getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            finish();
        }
    }
}
