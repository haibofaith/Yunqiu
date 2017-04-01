package com.kball.function.Login.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kball.C;
import com.kball.R;
import com.kball.base.BaseActivity;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.BaseListDataBean;
import com.kball.function.Login.bean.SelectBoundBean;
import com.kball.function.Login.interfaceView.AccountBindImpl;
import com.kball.function.Login.presenter.AccountBindPresenter;
import com.kball.function.Mine.custom.TitleView;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;
import com.mob.tools.utils.UIHandler;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by user on 2017/3/26.
 * /101.201.145.244:8081/center/inte/selectBound
 * 测试地址：47.93.119.150:2498/user/center/inte/selectBound
 */

public class AccountBindActivity extends BaseActivity implements AccountBindImpl, View.OnClickListener, PlatformActionListener, Handler.Callback {
    private TitleView title_view;
    private TextView qq_bindstatus;
    private TextView weixin_bindstatus;

    private RelativeLayout weixin_rlin;
    private RelativeLayout qq_rlin;

    private static final int MSG_USERID_FOUND = 1;
    private static final int MSG_LOGIN = 2;
    private static final int MSG_AUTH_CANCEL = 3;
    private static final int MSG_AUTH_ERROR = 4;
    private static final int MSG_AUTH_COMPLETE = 5;

    private AccountBindPresenter presenter;

    private static String openIdType = "0";//默认是0

    private RelativeLayout note_rlin;
    private TextView note_tv;
    private TextView cancle_tv;
    private TextView certain_tv;

    private String QQOpenId = "";
    private String WXOpenId = "";

    @Override
    protected int getContentViewResId() {
        return R.layout.account_bind_layout;
    }

    @Override
    protected void initView() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("账号绑定");
        weixin_bindstatus = (TextView) findViewById(R.id.weixin_bindstatus);
        qq_bindstatus = (TextView) findViewById(R.id.qq_bindstatus);
        weixin_rlin = (RelativeLayout) findViewById(R.id.weixin_rlin);
        qq_rlin = (RelativeLayout) findViewById(R.id.qq_rlin);
        note_rlin = (RelativeLayout) findViewById(R.id.note_rlin);
        note_tv = (TextView) findViewById(R.id.note_tv);
        cancle_tv = (TextView) findViewById(R.id.cancle_tv);
        certain_tv = (TextView) findViewById(R.id.certain_tv);
    }

    @Override
    protected void initData() {
        presenter = new AccountBindPresenter(this);
        presenter.selectBound(this);
    }

    @Override
    protected void setListener() {
        weixin_rlin.setOnClickListener(this);
        qq_rlin.setOnClickListener(this);
        cancle_tv.setOnClickListener(this);
        certain_tv.setOnClickListener(this);
    }

    @Override
    public void setSelectBoundData(BaseListDataBean<SelectBoundBean> result) {
        if ("1200".equals(result.getError_code())) {
            weixin_bindstatus.setText("未绑定");
            qq_bindstatus.setText("未绑定");
            for (SelectBoundBean bean : result.getData()) {
                //1：手机号 2：微信 3：QQ
                switch (bean.getIdentity_type()) {
                    case "1":
                        break;
                    case "2"://微信
                        weixin_bindstatus.setText("已绑定");
                        WXOpenId = bean.getIdentifier();
                        break;
                    case "3":
                        qq_bindstatus.setText("已绑定");
                        QQOpenId = bean.getIdentifier();
                        break;
                }
            }
        }
    }

    @Override
    public void setBoundData(BaseBean result) {
        if ("1200".equals(result.getError_code())) {
            switch (openIdType) {
                case "2"://微信
//                    ToastAlone.show("微信绑定成功");

                    break;
                case "3"://QQ
//                    ToastAlone.show("QQ绑定成功");
                    break;
            }
            presenter.selectBound(this);
        }
    }

    @Override
    public void setUnwrapData(BaseBean result) {
        if ("1200".equals(result.getError_code())) {
            switch (openIdType) {
                case "2"://微信
//                    ToastAlone.show("微信解除绑定成功");
                    break;
                case "3"://QQ
//                    ToastAlone.show("QQ解除绑定成功");
                    break;
            }
            presenter.selectBound(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.weixin_rlin:
                openIdType = "2";
                switch (weixin_bindstatus.getText().toString()) {
                    case "未绑定":
                        SPUtil.getInstance().putString(C.SP.OPEN_ID_TYPE, openIdType);
                        authorize(new Wechat(this));
                        break;
                    case "已绑定":
                        note_rlin.setVisibility(View.VISIBLE);
                        note_tv.setText("是否解绑微信授权?");
                        break;
                }
                break;
            case R.id.qq_rlin:
                openIdType = "3";
                switch (qq_bindstatus.getText().toString()) {
                    case "未绑定":
                        SPUtil.getInstance().putString(C.SP.OPEN_ID_TYPE, openIdType);
                        authorize(new QQ(this));
                        break;
                    case "已绑定":
                        note_rlin.setVisibility(View.VISIBLE);
                        note_tv.setText("是否解绑QQ授权?");
                        break;
                }
                break;
            case R.id.certain_tv:
                switch (openIdType){
                    case "2"://微信
                        presenter.unwrap(this, WXOpenId, openIdType);
                        break;
                    case "3"://QQ
                        presenter.unwrap(this, QQOpenId, openIdType);
                        break;
                }
                note_rlin.setVisibility(View.GONE);
                break;
            case R.id.cancle_tv:
                note_rlin.setVisibility(View.GONE);
                break;
        }
    }

    private void authorize(Platform plat) {
        //是否已经授权
        if (plat.isValid()) {
            String userId = plat.getDb().getUserId();
            if (!TextUtils.isEmpty(userId)) {
                //已经授权
                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
                SPUtil.getInstance().putString(C.SP.OPENID, plat.getDb().getUserId());
                return;
            }
        }
        plat.setPlatformActionListener(this);
        plat.SSOSetting(true);
        plat.showUser(null);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_USERID_FOUND: {
                Toast.makeText(this, R.string.userid_found, Toast.LENGTH_SHORT).show();
                //此处写登录接口
                presenter.bound(this, SPUtil.getInstance().getString(C.SP.OPENID, ""), openIdType);
            }
            break;
            case MSG_LOGIN: {
                presenter.bound(this, SPUtil.getInstance().getString(C.SP.OPENID, ""), openIdType);
            }
            break;
            case MSG_AUTH_CANCEL: {
                Toast.makeText(this, R.string.auth_cancel, Toast.LENGTH_SHORT).show();
                System.out.println("-------MSG_AUTH_CANCEL--------");
            }
            break;
            case MSG_AUTH_ERROR: {
//                Toast.makeText(this, R.string.auth_error, Toast.LENGTH_SHORT).show();
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
            SPUtil.getInstance().putString(C.SP.OPENID, platform.getDb().getUserId());
            login(platform.getName(), platform.getDb().getUserId(), res);
        }
        System.out.println(res);
        SPUtil.getInstance().putString(C.SP.OPENID, platform.getDb().getUserId());
        presenter.bound(this, SPUtil.getInstance().getString(C.SP.OPENID, ""), openIdType);
    }

    private void login(String plat, String userId, HashMap<String, Object> userInfo) {
        Message msg = new Message();
        msg.what = MSG_LOGIN;
        msg.obj = plat;
        UIHandler.sendMessage(msg, this);
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
