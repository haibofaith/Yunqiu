package com.kball.function.Match.presenter;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.bean.BaseListBean;
import com.kball.function.CloudBall.ui.AddMatchOrPeopleAct;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.function.Login.ui.PassLoginActivity;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.function.Match.impls.MatchListView;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.PublicUtil;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by user on 2017/3/4.
 */

public class MatchListPresenter {
    MatchListView matchListView;

    public MatchListPresenter(MatchListView matchListView) {
        this.matchListView = matchListView;
    }

    public void selectGameList(final Context context, String pageNum, String pageSize, String game_status, String
            game_time, String team_id,boolean refreshFlag) {

        if ("".equals(SPUtil.getInstance().getString(C.SP.USER_ID, ""))) {
        } else {

            NetRequest.getInstance().get(context, NI.selectGameList(pageNum, pageSize, game_status, game_time,
                    team_id), new RequestHandler() {

                @Override
                public void onStart() {
                    matchListView.showLoading();
                }

                @Override
                public void onSuccess(String response) {
                    JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                    if (1200 == jsonObject.get("error_code").getAsInt()) {
                        BaseBean<BaseListBean<MatchListBean<MatchGameBean>>> result;
                        Gson gson = new Gson();
                        Type type = new TypeToken<BaseBean<BaseListBean<MatchListBean<MatchGameBean>>>>() {
                        }.getType();
                        result = gson.fromJson(response.toString(), type);
//                            mImpl.toastMsg("登录成功");
                        matchListView.setListData(result);
                    } else if (1504 == jsonObject.get("error_code").getAsInt()) {
                        PublicUtil.refreshToken(context);
                    } else {
//                        ToastAlone.show(jsonObject.get("msg").getAsString());
                        if ("缺少用户id跟token".equals(jsonObject.get("msg").getAsString())){
                            SPUtil.getInstance().putString(C.SP.USER_ID,"");
                            SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN,"");
                        }
                    }
                }

                @Override
                public void onFinish() {
                    matchListView.dismissLoading();
                }
            });
        }
    }

}
