package com.kball.function.CloudBall.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.function.CloudBall.bean.AreaBean;
import com.kball.function.CloudBall.bean.CityBean;
import com.kball.function.CloudBall.bean.ProviceBean;
import com.kball.function.CloudBall.view.PlaceView;
import com.kball.function.CloudBall.view.ProviceView;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.SPUtil;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/14.
 */

public class ProvicePresenter {
    private ProviceView mImpl;
        Context mContext;
        public ProvicePresenter(Context mContext, ProviceView mImpl) {
            this.mContext = mContext;
            this.mImpl = mImpl;
        }


        public void selectProvince() {
        NetRequest.getInstance().get(mContext, NI.selectProvince(), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    ListBaseBean<ProviceBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<ListBaseBean<ProviceBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
//                            mImpl.toastMsg("登录成功");
                    mImpl.setProvice(result.getData());
                } else {

                }
            }

            @Override
            public void onFinish() {

            }
        });
        }

    public void selectCity(String str) {
        NetRequest.getInstance().get(mContext, NI.selectCity(str), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    ListBaseBean<CityBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<ListBaseBean<CityBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
//                            mImpl.toastMsg("登录成功");
                    mImpl.setCity(result.getData());
                } else {

                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void selectArea(String city_id) {
        NetRequest.getInstance().get(mContext, NI.selectArea(city_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    ListBaseBean<AreaBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<ListBaseBean<AreaBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
                    mImpl.setArea(result.getData());
                } else {

                }
            }

            @Override
            public void onFinish() {

            }
        });
    }
}