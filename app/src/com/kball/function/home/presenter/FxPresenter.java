package com.kball.function.home.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.BaseListDataBean;
import com.kball.function.Match.bean.VideoBean;
import com.kball.function.home.bean.BannerBean;
import com.kball.function.home.bean.FxListBean;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.function.home.bean.MyInfoBaseBean;
import com.kball.function.home.impl.FxImpl;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.PublicUtil;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by user on 2017/3/21.
 */

public class FxPresenter {
    private FxImpl fx;

    public FxPresenter(FxImpl fx) {
        this.fx = fx;
    }

//    /101.201.145.244:8085/found/inte/index
    public void index(final Context context){
        NetRequest.getInstance().get(context, NI.index(), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);

                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseListDataBean<BannerBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseListDataBean<BannerBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
                    fx.setIndexData(result);
                }
                else if(1504 == jsonObject.get("error_code").getAsInt()){
                    PublicUtil.refreshToken(context);
                }
                else {
                    ToastAlone.show(jsonObject.get("msg")
                            .getAsString());
                    if ("缺少用户id跟token".equals(jsonObject.get("msg").getAsString())){
                        SPUtil.getInstance().putString(C.SP.USER_ID,"");
                        SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN,"");
                    }
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void getVideo(Context context, String league_id){
        NetRequest.getInstance().get(context, NI.getVideo("3",league_id,"1","10"), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<BaseListBean<VideoBean>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<BaseListBean<VideoBean>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
                    fx.setGetVideoData(result);
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
