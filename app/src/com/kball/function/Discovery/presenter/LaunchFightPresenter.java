package com.kball.function.Discovery.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.bean.BaseListBean;
import com.kball.function.Discovery.bean.DisBaseListBean;
import com.kball.function.Discovery.bean.LaunchFightListBean;
import com.kball.function.Discovery.impl.LaunchFightImpl;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchTabTwoBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by user on 2017/3/24.
 */

public class LaunchFightPresenter {
    LaunchFightImpl launchFight;

    public LaunchFightPresenter(LaunchFightImpl launchFight) {
        this.launchFight = launchFight;
    }

    public void getAboutGameList(Context context){
        NetRequest.getInstance().get(context, NI.getAboutGameList(), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<DisBaseListBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<DisBaseListBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
                    launchFight.setGetAboutGameListData(result);
                } else {
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
}
