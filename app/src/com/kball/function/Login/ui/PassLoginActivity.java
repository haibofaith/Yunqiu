package com.kball.function.Login.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.BaseListDataBean;
import com.kball.function.Login.bean.SelectBoundBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.function.Login.interfaceView.LoginView;
import com.kball.function.Login.presenter.LoginPresenter;
import com.kball.function.Mine.custom.TitleView;
import com.kball.function.home.ui.HomeActivity;
import com.kball.function.other.CircleImageView;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;
import com.mob.tools.utils.UIHandler;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

import static com.kball.C.SP.USER_INFO;

/**
 * Created by user on 2017/2/21.
 */

public class PassLoginActivity extends BaseActivity implements View.OnClickListener, LoginView, Handler.Callback, PlatformActionListener {
    private TitleView title_view;
    private TextView register_tv;//注册
    private LinearLayout quick_login_lin;
    private ImageView weixin_login;//微信登陆
    private ImageView qq_login;//微信登陆
    private TextView forget_pass_tv;//忘记密码
    private TextView join_yunqiu_tv;//登陆
    private LoginPresenter mPresenter;

    private EditText phone_edit;
    private EditText code_edit;

    private static final int MSG_USERID_FOUND = 1;
    private static final int MSG_LOGIN = 2;
    private static final int MSG_AUTH_CANCEL = 3;
    private static final int MSG_AUTH_ERROR = 4;
    private static final int MSG_AUTH_COMPLETE = 5;

    private static String openIdType = "0";//默认是0
    private CircleImageView imageView2;
    private static String identity_type = "0";

    private static String other_ACCESS_TOKEN;

    @Override
    protected int getContentViewResId() {
        return R.layout.pass_login_layout;
    }

    @Override
    protected void initData() {
        mPresenter = new LoginPresenter(this, mContext);
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        register_tv = (TextView) findViewById(R.id.register_tv);
        join_yunqiu_tv = (TextView) findViewById(R.id.join_yunqiu_tv);
        forget_pass_tv = (TextView) findViewById(R.id.forget_pass_tv);
        weixin_login = (ImageView) findViewById(R.id.weixin_login);
        qq_login = (ImageView) findViewById(R.id.qq_login);
        quick_login_lin = (LinearLayout) findViewById(R.id.quick_login_lin);
        title_view.setTitleText("");
        title_view.setLeftButtonText("关闭");
        title_view.setLeftButtonImgGone();
        phone_edit = (EditText) findViewById(R.id.phone_edit);
        code_edit = (EditText) findViewById(R.id.code_edit);
        imageView2 = (CircleImageView) findViewById(R.id.imageView2);
    }


    @Override
    protected void setListener() {
        register_tv.setOnClickListener(this);
        quick_login_lin.setOnClickListener(this);
        weixin_login.setOnClickListener(this);
        qq_login.setOnClickListener(this);
        forget_pass_tv.setOnClickListener(this);
        join_yunqiu_tv.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                ToastAlone.show("注册成功");
                setResult(RESULT_OK);
                finish();
            }
        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case C.SP.BindLoginCode:
                    //绑定注册成功
                    ToastAlone.show("绑定登陆成功");
                    setResult(RESULT_OK);
                    finish();
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_tv:
                startActivityForResult(new Intent(this, RegisterActivity.class), 123);
                break;
            case R.id.quick_login_lin:
                startActivity(new Intent(this, CodeLoginActivity.class));
                finish();
                break;
            case R.id.weixin_login:
//                startActivity(new Intent(this, BindPhoneActivity.class));
                openIdType = "2";
                SPUtil.getInstance().putString(C.SP.OPEN_ID_TYPE, openIdType);
                authorize(new Wechat(this));
                break;
            case R.id.qq_login:
                openIdType = "3";
                SPUtil.getInstance().putString(C.SP.OPEN_ID_TYPE, openIdType);
                authorize(new QQ(this));
                break;
            case R.id.forget_pass_tv:
                startActivity(new Intent(this, ModifyCodeActivity.class));
                break;
            case R.id.join_yunqiu_tv:
                if (phone_edit.getText().toString() == null || "".equals(phone_edit.getText().toString())) {
                    ToastAlone.show("请输入手机号");
                    return;
                }
                if (code_edit.getText().toString() == null || "".equals(code_edit.getText().toString())) {
                    ToastAlone.show("请输入密码");
                    return;
                }
                mPresenter.login("1", phone_edit.getText().toString(), code_edit.getText().toString());
                break;
        }
    }

    @Override
    public void toastMsg(String msg) {
        ToastAlone.show(msg);
    }

    @Override
    public void setObjData(BaseBean<UserRegisterBean> result) {
        if (result != null && "1200".equals(result.getError_code())) {
            ToastAlone.show("登陆成功");
            SPUtil.getInstance().putString(C.SP.USER_ID, result.getData().getUser_id());
            SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN, result.getData().getAccess_token());
            SPUtil.getInstance().putString(C.SP.EXPIRES_IN, result.getData().getExpires_in());
            SPUtil.getInstance().putString(C.SP.REFRESH_TOKEN, result.getData().getRefresh_token());
            SPUtil.getInstance().putString(C.SP.BOUND_STATUS, result.getData().getBound_status());
            SPUtil.getInstance().putString(C.SP.OPENID, result.getData().getOpenid());
            //如果有bound_status=2，access_token是微信的，调用绑定接口时返回，如果没有bound_status=2，则表示登陆成功，access_token是运球APP内部的
            if ("2".equals(result.getData().getBound_status())) {
                SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN, other_ACCESS_TOKEN);
                Intent intent = new Intent(this, BindPhoneActivity.class);
                startActivityForResult(intent, C.SP.BindLoginCode);
            } else {
                if ("" != SPUtil.getInstance().getString(C.SP.USER_ID, "")) {
                    Set<String> tags = new HashSet<>();
                    tags.add("test");
                    JPushInterface.setAliasAndTags(this, SPUtil.getInstance().getString(C.SP.USER_ID, ""), tags,
                            new TagAliasCallback() {
                        @Override
                        public void gotResult(int i, String s, Set<String> set) {
                            if (i == 0) {
                                Log.d("JPush", "调用成功,alias" + s + "标签" + set);
                            }
                        }
                    });
                }
                setResult(RESULT_OK);
                finish();
            }
        }
    }

    @Override
    public void bindPhone() {
        Intent intent = new Intent(this, BindPhoneActivity.class);
        startActivityForResult(intent, C.SP.BindLoginCode);
    }

    @Override
    public void setSelectBoundData(BaseListDataBean<SelectBoundBean> result) {
        if ("1200".equals(result.getError_code())) {
            for (SelectBoundBean bean : result.getData()) {
                //1：手机号 2：微信 3：QQ
                if ("1".equals(bean.getIdentity_type())) {
                    setResult(RESULT_OK);
                    finish();
                    return;
                } else {
                    if ("3".equals(bean.getIdentity_type())) {
                        Intent intent = new Intent(this, BindPhoneActivity.class);
                        startActivityForResult(intent, C.SP.BindLoginCode);
                    }
                }
            }
        }
    }

    private void authorize(Platform plat) {
        //是否已经授权
        if (plat.isValid()) {
            String userId = plat.getDb().getUserId();
            if (!TextUtils.isEmpty(userId)) {
                //已经授权
                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
                SPUtil.getInstance().putString(C.SP.USER_NAME, plat.getDb().getUserName());
                SPUtil.getInstance().putString(C.SP.OPENID, plat.getDb().getUserId());
                other_ACCESS_TOKEN = plat.getDb().getToken();
//                SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN,plat.getDb().getToken());
                SPUtil.getInstance().putString(C.SP.USER_ICON, plat.getDb().getUserIcon());
                return;
            }
        }
        plat.setPlatformActionListener(this);
        plat.SSOSetting(true);
        plat.showUser(null);
    }

    private void login(String plat, String userId, HashMap<String, Object> userInfo) {
        Message msg = new Message();
        msg.what = MSG_LOGIN;
        msg.obj = plat;
        UIHandler.sendMessage(msg, this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_USERID_FOUND: {
                Toast.makeText(this, R.string.userid_found, Toast.LENGTH_SHORT).show();
                //此处写登录接口
                identity_type = openIdType;
                if (!"0".equals(identity_type)) {
                    mPresenter.login(identity_type, SPUtil.getInstance().getString(C.SP.OPENID, ""), "");
                }
            }
            break;
            case MSG_LOGIN: {
                String text = getString(R.string.logining, msg.obj);
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                System.out.println("---------------");
                Intent intent = new Intent(this, BindPhoneActivity.class);
                startActivityForResult(intent, C.SP.BindLoginCode);
            }
            break;
            case MSG_AUTH_CANCEL: {
                Toast.makeText(this, R.string.auth_cancel, Toast.LENGTH_SHORT).show();
                System.out.println("-------MSG_AUTH_CANCEL--------");
            }
            break;
            case MSG_AUTH_ERROR: {
                Toast.makeText(this, R.string.auth_error, Toast.LENGTH_SHORT).show();
                System.out.println("-------MSG_AUTH_ERROR--------");
            }
            break;
            case MSG_AUTH_COMPLETE: {
                Toast.makeText(this, R.string.auth_complete, Toast.LENGTH_SHORT).show();
                System.out.println("--------MSG_AUTH_COMPLETE-------");
            }
            break;
        }
        return false;
    }

    public void onComplete(Platform platform, int action,
                           HashMap<String, Object> res) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_COMPLETE, this);
            SPUtil.getInstance().putString(C.SP.USER_NAME, platform.getDb().getUserName());
            SPUtil.getInstance().putString(C.SP.OPENID, platform.getDb().getUserId());
            SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN, platform.getDb().getToken());
            SPUtil.getInstance().putString(C.SP.USER_ICON, platform.getDb().getUserIcon());
            login(platform.getName(), platform.getDb().getUserId(), res);
        }
        System.out.println(res);
        SPUtil.getInstance().putString(C.SP.USER_NAME, platform.getDb().getUserName());
        SPUtil.getInstance().putString(C.SP.OPENID, platform.getDb().getUserId());
        SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN, platform.getDb().getToken());
        SPUtil.getInstance().putString(C.SP.USER_ICON, platform.getDb().getUserIcon());

        System.out.println("------User Name ---------" + platform.getDb().getUserName());
        System.out.println("------User ID ---------" + platform.getDb().getUserId());
        System.out.println("------User Token ---------" + platform.getDb().getToken());
        System.out.println("------User Icon ---------" + platform.getDb().getUserIcon());
//        Intent intent = new Intent(this,BindPhoneActivity.class);
//        intent.putExtra("openIdType",openIdType);
//        intent.putExtra("accessToken",platform.getDb().getToken());
//        startActivity(intent);


    }

    public void onError(Platform platform, int action, Throwable t) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_ERROR, this);
        }
        t.printStackTrace();
    }

    public void onCancel(Platform platform, int action) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_CANCEL, this);
        }
    }
}