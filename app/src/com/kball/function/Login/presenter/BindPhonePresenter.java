package com.kball.function.Login.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.function.Login.interfaceView.BindPhoneView;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by user on 2017/3/3.
 */

public class BindPhonePresenter {
    BindPhoneView bindPhoneView;

    public BindPhonePresenter(BindPhoneView bindPhoneView) {
        this.bindPhoneView = bindPhoneView;
    }

    public void bound(Context context,String phone,String verifycode){
        NetRequest.getInstance().post(context, NI.bound(phone, verifycode, SPUtil.getInstance().getString(C.SP.OPENID,""),
                SPUtil.getInstance().getString(C.SP.OPEN_ID_TYPE,""),SPUtil.getInstance().getString(C.SP.ACCESS_TOKEN,""), SPUtil.getInstance().getString(C.SP.USER_NAME,""),
                SPUtil.getInstance().getString(C.SP.USER_ICON,"")), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);

                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<UserRegisterBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<UserRegisterBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
//                            mImpl.toastMsg("登录成功");
                    bindPhoneView.setBoundResult(result);
                } else {
                    ToastAlone.show(jsonObject.get("msg")
                            .getAsString());
                }
            }

            @Override
            public void onFinish() {

            }
        });

    }

    //获取验证码
    public void getVerifycode(Context context, String type, String phone) {
        NetRequest.getInstance().post(context, NI.getVerifycode(type, phone), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);

                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
                    bindPhoneView.setVerifycode(result);
                } else {
                    ToastAlone.show(jsonObject.get("msg")
                            .getAsString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
