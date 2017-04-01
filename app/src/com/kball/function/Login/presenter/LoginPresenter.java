package com.kball.function.Login.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.BaseListDataBean;
import com.kball.function.Login.bean.SelectBoundBean;
import com.kball.function.Login.bean.UserInfoBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.function.Login.interfaceView.LoginView;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/2/28.
 */

public class LoginPresenter {
    private LoginView mImpl;
    private Context mContext;

    public LoginPresenter(LoginView mImpl, Context mContext) {
        this.mImpl = mImpl;
        this.mContext = mContext;
    }

    public void login(String identity_type, String identifier) {
        final String identity_type_flag = identity_type;
        NetRequest.getInstance().get(
                mContext,
                NI.login(identity_type, identifier), new RequestHandler() {

                    @Override
                    public void onStart() {
                        mImpl.showLoading();
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
                            result = gson.fromJson(response.toString(), type);
//                            mImpl.toastMsg("登录成功");
                            mImpl.setObjData(result);
                        } else {
                            ToastAlone.show(jsonObject.get("msg")
                                    .getAsString());
                        }
                    }

                    @Override
                    public void onFinish() {
                        mImpl.dismissLoading();
                    }

                    @Override
                    public void onFailure(String responseBody, Throwable error) {
                        if ("2".equals(identity_type_flag) || "3".equals(identity_type_flag)) {
                            mImpl.bindPhone();
                        }else{
                            super.onFailure(responseBody, error);
                        }
                    }
                });

    }

    public void login(String identity_type, String identifier, String credential) {
        final String identity_type_flag = identity_type;
        NetRequest.getInstance().get(
                mContext,
                NI.login(identity_type, identifier, credential), new RequestHandler() {

                    @Override
                    public void onStart() {
                        mImpl.showLoading();
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
                            result = gson.fromJson(response.toString(), type);
//                            mImpl.toastMsg("登录成功");
                            mImpl.setObjData(result);
                        } else {
                            ToastAlone.show(jsonObject.get("msg")
                                    .getAsString());
                        }
                    }

                    @Override
                    public void onFinish() {
                        mImpl.dismissLoading();
                    }

                    @Override
                    public void onFailure(String responseBody, Throwable error) {
                    }
                });

    }


    public void selectBound() {
        NetRequest.getInstance().get(
                mContext,
                NI.selectBound(), new RequestHandler() {

                    @Override
                    public void onStart() {
                        mImpl.showLoading();
                    }

                    @Override
                    public void onSuccess(String response) {
                        JsonObject jsonObject = new Gson().fromJson(response,
                                JsonObject.class);

                        if (1200 == jsonObject.get("error_code").getAsInt()) {
                            BaseListDataBean<SelectBoundBean> result;
                            Gson gson = new Gson();
                            Type type = new TypeToken<BaseListDataBean<SelectBoundBean>>() {
                            }.getType();
                            result = gson.fromJson(response.toString(), type);
//                            mImpl.toastMsg("登录成功");
                            mImpl.setSelectBoundData(result);
                        } else {
                            ToastAlone.show(jsonObject.get("msg")
                                    .getAsString());
                        }
                    }

                    @Override
                    public void onFinish() {
                        mImpl.dismissLoading();
                    }

                    @Override
                    public void onFailure(String responseBody, Throwable error) {

                    }
                });

    }

}
