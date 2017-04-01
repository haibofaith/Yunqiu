package com.kball.function.CloudBall.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.function.CloudBall.ui.RanksDetailAct;
import com.kball.function.CloudBall.view.RanksDetailImpl;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.LeagueInfoBean;
import com.kball.function.Match.bean.LeagueInfoData;
import com.kball.function.Match.view.MatchInfoViews;
import com.kball.function.home.bean.RanksBaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/7.
 */

public class RanksDetailPresenter {
    RanksDetailImpl matchListView;

    public RanksDetailPresenter(RanksDetailImpl matchListView) {
        this.matchListView = matchListView;
    }

    public void indexTeamInfo(Context context, String league_id) {
        NetRequest.getInstance().get(context, NI.indexTeamInfo(league_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<RanksBaseBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<RanksBaseBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
//                            mImpl.toastMsg("登录成功");
                    matchListView.setListInfoData(result);
                } else {
                    ToastAlone.show(jsonObject.get("msg").getAsString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void changeTeamInvite(Context context, String yaoqingma, String team_id) {
        NetRequest.getInstance().post(context, NI.updateTeamInvite(yaoqingma, team_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    matchListView.setInvite();
                } else {
                    ToastAlone.show(jsonObject.get("msg").getAsString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void applyJoinTeam(Context context, String team_id, String joinStr) {
        NetRequest.getInstance().post(context, NI.applyJoinTeam(joinStr, team_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    matchListView.applyJoinTeam();
                } else {
                    ToastAlone.show(jsonObject.get("msg").getAsString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void dissolveTeam(Context context, String team_id) {
        NetRequest.getInstance().post(context, NI.dissolveTeam(team_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    matchListView.dissolveTeam();
                } else {
                    ToastAlone.show(jsonObject.get("msg").getAsString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void attentionTeam(Context context, String team_id) {
        NetRequest.getInstance().post(context, NI.attentionTeam(team_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    matchListView.attentionTeam();
                } else {
                    ToastAlone.show(jsonObject.get("msg").getAsString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void cancelAttentionTeam(Context context, String team_id) {
        NetRequest.getInstance().post(context, NI.cancelAttentionTeam(team_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    matchListView.cancelAttentionTeam();
                } else {
                    ToastAlone.show(jsonObject.get("msg").getAsString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void applyJoinTeamByInvite(Context context, String team_id, String joinStr) {
        NetRequest.getInstance().post(context, NI.applyJoinTeamByInvite(joinStr, team_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    matchListView.applyJoinTeamByInvite();
                } else {
                    ToastAlone.show(jsonObject.get("msg").getAsString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void auditInvitation(Context context, String yq_str, String s) {
        NetRequest.getInstance().post(context, NI.auditInvitation(yq_str, s), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    matchListView.auditInvitation();
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
