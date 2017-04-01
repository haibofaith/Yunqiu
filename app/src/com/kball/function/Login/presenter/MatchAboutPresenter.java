package com.kball.function.Login.presenter;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.GameInfoBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.function.Match.bean.MemberBean;
import com.kball.function.Match.impls.MatchAboutViews;
import com.kball.function.Match.view.IntegralDetailViews;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/6.
 */

public class MatchAboutPresenter {

    MatchAboutViews matchListView;


    public MatchAboutPresenter(MatchAboutViews matchListView) {
        this.matchListView = matchListView;
    }

    public void selectGameInfo(Context context, String game_id) {
        NetRequest.getInstance().get(context, NI.selectGameInfo(game_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<GameInfoBean<MemberBean>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<GameInfoBean<MemberBean>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
//                            mImpl.toastMsg("登录成功");
                    matchListView.setData(result);
                } else {
                    ToastAlone.show(jsonObject.get("msg").getAsString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void auditOrFightGame(Context context, String game_id, String type, String comment, String cause, String
            uniform_teamB) {
        NetRequest.getInstance().post(context, NI.auditOrFightGame(game_id, type, comment, cause, uniform_teamB), new
                RequestHandler() {
            @Override
            public void onStart() {

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
                    matchListView.setAuditOrFightGameData(result);
                } else {
                    ToastAlone.show(jsonObject.get("msg").getAsString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void cancelGame(Activity context, String game_id, String cancleStr) {
        NetRequest.getInstance().post(context, NI.cancelGame(game_id, cancleStr), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    matchListView.cancleGame();
                } else {
                    ToastAlone.show(jsonObject.get("msg").getAsString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void participationGame(Context context, String game_id, String team_id, String join_status) {
        NetRequest.getInstance().post(context, NI.participationGame(game_id, team_id, join_status), new
                RequestHandler() {
            @Override
            public void onStart() {

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

            }
        });
    }
}