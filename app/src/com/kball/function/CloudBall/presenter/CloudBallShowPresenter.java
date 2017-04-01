package com.kball.function.CloudBall.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.bean.BaseListBean;
import com.kball.function.CloudBall.bean.CloudBallShowBaseBean;
import com.kball.function.CloudBall.bean.DuihuiBean;
import com.kball.function.CloudBall.bean.GameBean;
import com.kball.function.CloudBall.bean.MatchBean;
import com.kball.function.CloudBall.bean.RankBaseBean;
import com.kball.function.CloudBall.bean.RankInfoBean;
import com.kball.function.CloudBall.bean.RecommendTeam;
import com.kball.function.CloudBall.view.CloudBallShowActViews;
import com.kball.function.CloudBall.view.RanksDetailImpl;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.function.home.bean.RanksBaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/8.
 */

public class CloudBallShowPresenter {
    CloudBallShowActViews matchListView;

    public CloudBallShowPresenter(CloudBallShowActViews matchListView) {
        this.matchListView = matchListView;
    }

    public void getDuihui(Context context) {
//
//        if ( SPUtil.getInstance().getString("home_str","").length() == 0){
//
//        }else{
//            BaseBean<CloudBallShowBaseBean<DuihuiBean>> result;
//            Gson gson = new Gson();
//            Type type = new TypeToken<BaseBean<CloudBallShowBaseBean<DuihuiBean>>>() {
//            }.getType();
//            result = gson.fromJson(SPUtil.getInstance().getString("home_str",""), type);
//            matchListView.setInfoData(result.getData().getInfo(), result.getData().getType());
//        }

        NetRequest.getInstance().get(context, NI.getDuihui(), new RequestHandler() {
            @Override
            public void onStart() {
                matchListView.showLoading();
            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<CloudBallShowBaseBean<DuihuiBean>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<CloudBallShowBaseBean<DuihuiBean>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
                    SPUtil.getInstance().putString("home_str",response);
                    matchListView.setInfoData(result.getData().getInfo(), result.getData().getType());
                } else {
                    if ("缺少用户id跟token".equals(jsonObject.get("msg").getAsString())) {
                        SPUtil.getInstance().putString(C.SP.USER_ID, "");
                        SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN, "");
                    }
                }
            }

            @Override
            public void onFinish() {
                matchListView.dismissLoading();
            }
        });
    }

    public void getRankInfo(Context context, String team_id, final int position) {


        NetRequest.getInstance().get(context, NI.getRankInfo(team_id), new RequestHandler() {
            @Override
            public void onStart() {
                matchListView.showLoading();
            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<RankBaseBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<RankBaseBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
                    matchListView.setInfo(result, position);
                    SPUtil.getInstance().putString("headDataCache", response);
                } else {
                }
            }

            @Override
            public void onFinish() {
                matchListView.dismissLoading();
            }
        });
    }

    public void participationGame(Context context, String game_id, String team_id, String join_status) {
        NetRequest.getInstance().post(context, NI.participationGame(game_id, team_id, join_status), new
                RequestHandler() {
            @Override
            public void onStart() {
                matchListView.showLoading();
            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
                    matchListView.setParticipationGameData(result);
                } else {
                    ToastAlone.show(jsonObject.get("msg").getAsString());
                }
            }

            @Override
            public void onFinish() {
                matchListView.dismissLoading();
            }
        });
    }
}
