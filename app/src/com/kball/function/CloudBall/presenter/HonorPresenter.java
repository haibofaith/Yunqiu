package com.kball.function.CloudBall.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.function.CloudBall.bean.CloudBallHonorBean;
import com.kball.function.CloudBall.bean.RankPeopleBean;
import com.kball.function.CloudBall.view.BallPlayerImpl;
import com.kball.function.CloudBall.view.HonorView;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.SPUtil;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/22.
 */

public class HonorPresenter {
    private HonorView mImpl;
    Context mContext;
    public HonorPresenter(Context mContext, HonorView mImpl) {
        this.mContext = mContext;
        this.mImpl = mImpl;
    }


    public void getdata(String team_id) {
        NetRequest.getInstance().get(mContext, NI.honor(team_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    ListBaseBean<CloudBallHonorBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<ListBaseBean<CloudBallHonorBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
//                            mImpl.toastMsg("登录成功");
                    mImpl.setInfoData(result.getData());
                } else {
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
