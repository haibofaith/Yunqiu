package com.kball.function.Login.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.function.Login.interfaceView.BindPhoneView;
import com.kball.function.Login.presenter.BindPhonePresenter;
import com.kball.function.Mine.custom.TitleView;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

/**
 * Created by user on 2017/2/21.
 */

public class BindPhoneActivity extends BaseActivity implements View.OnClickListener, BindPhoneView {
    private TitleView title_view;
    private TextView join_yunqiu_tv;//加入云球
    private BindPhonePresenter presenter;
    private static String openIdType;
    private EditText phone_edit;
    private EditText code_edit;
    private ImageView recive_code_img;
    private String accessToken;
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
        return R.layout.bind_phone_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("绑定手机号");
        join_yunqiu_tv = (TextView) findViewById(R.id.join_yunqiu_tv);
        phone_edit = (EditText) findViewById(R.id.phone_edit);
        code_edit = (EditText) findViewById(R.id.code_edit);
        recive_code_img = (ImageView) findViewById(R.id.recive_code_img);

        djs_tv = (TextView)findViewById(R.id.djs_tv);
    }

    @Override
    protected void initData() {
        openIdType = SPUtil.getInstance().getString(C.SP.OPEN_ID_TYPE,"");
        accessToken = SPUtil.getInstance().getString(C.SP.ACCESS_TOKEN,"");
        presenter = new BindPhonePresenter(this);
    }

    @Override
    protected void setListener() {
        join_yunqiu_tv.setOnClickListener(this);
        recive_code_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.join_yunqiu_tv:
                presenter.bound(this, phone_edit.getText().toString(), code_edit.getText().toString());
                break;
            case R.id.recive_code_img:
                if (TextUtils.isEmpty(phone_edit.getText().toString())){
                    ToastAlone.show("请输入手机号");
                    return;
                }
                presenter.getVerifycode(this, openIdType, phone_edit.getText().toString());
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
    public void setBoundResult(BaseBean<UserRegisterBean> result) {
        if (result != null && "1200".equals(result.getError_code())) {
            SPUtil.getInstance().putString(C.SP.USER_ID, result.getData().getUser_id());
            SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN, result.getData().getAccess_token());
            SPUtil.getInstance().putString(C.SP.EXPIRES_IN, result.getData().getExpires_in());
            SPUtil.getInstance().putString(C.SP.REFRESH_TOKEN, result.getData().getRefresh_token());
            Intent intent = new Intent(this, PerfectInfoActivity.class);
            startActivityForResult(intent,C.SP.PerfectLoginCode);
//            setResult(RESULT_OK);
//            finish();
        }
    }

    @Override
    public void setVerifycode(BaseBean result) {
        if ("1200".equals(result.getError_code())) {
            ToastAlone.show(result.getMsg());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case C.SP.PerfectLoginCode:
                    setResult(RESULT_OK);
                    finish();
                    break;
            }
        }
    }
}
