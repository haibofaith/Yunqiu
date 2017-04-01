package com.kball.function.Login.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.UserInfoBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.function.Login.interfaceView.CodeLoginView;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by user on 2017/2/28.
 */

public class CodeLoginPresenter {
    CodeLoginView codeLoginView;

    public CodeLoginPresenter(CodeLoginView codeLoginView) {
        this.codeLoginView = codeLoginView;
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
                    codeLoginView.setVerifycode(result);
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

    public void login(Context mContext,String identity_type,String identifier,String credential) {
        NetRequest.getInstance().get(
                mContext,
                NI.login(identity_type,identifier,credential), new RequestHandler() {

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
                            codeLoginView.setObjData(result);

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
