package com.kball.function.Login.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.BaseListDataBean;
import com.kball.function.Login.bean.SelectBoundBean;
import com.kball.function.Login.interfaceView.AccountBindImpl;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by user on 2017/3/26.
 */

public class AccountBindPresenter {
    AccountBindImpl accountBind;

    public AccountBindPresenter(AccountBindImpl accountBind) {
        this.accountBind = accountBind;
    }

    public void selectBound(Context context) {
        NetRequest.getInstance().get(
                context,
                NI.selectBound(), new RequestHandler() {

                    @Override
                    public void onStart() {
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
                            accountBind.setSelectBoundData(result);
                        } else {
                            ToastAlone.show(jsonObject.get("msg")
                                    .getAsString());
                        }
                    }

                    @Override
                    public void onFinish() {
                    }

                    @Override
                    public void onFailure(String responseBody, Throwable error) {

                    }
                });
    }

    ///101.201.145.244:8081/center/inte/bound
    //测试地址：47.93.119.150:2498/user/center/inte/bound
    public void bound(Context context,String identifier,String type){
        NetRequest.getInstance().post(context, NI.bound(identifier, type), new RequestHandler() {
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
                    result = gson.fromJson(response.toString(), type);
//                            mImpl.toastMsg("登录成功");
                    accountBind.setBoundData(result);
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


    ///101.201.145.244:8081/center/inte/unwrap
    //测试地址：47.93.119.150:2498/user/center/inte/unwrap
    public void unwrap(Context context,String identifier,String type){
        NetRequest.getInstance().post(context, NI.unwrap(identifier, type), new RequestHandler() {
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
                    result = gson.fromJson(response.toString(), type);
//                            mImpl.toastMsg("登录成功");
                    accountBind.setUnwrapData(result);
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
