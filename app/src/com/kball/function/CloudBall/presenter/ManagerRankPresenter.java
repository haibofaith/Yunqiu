package com.kball.function.CloudBall.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.function.CloudBall.bean.RankPeopleBean;
import com.kball.function.CloudBall.view.BallPlayerImpl;
import com.kball.function.CloudBall.view.ManagerRankView;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/18.
 */

public class ManagerRankPresenter {
    private ManagerRankView mImpl;
    Context mContext;

    public ManagerRankPresenter(Context mContext, ManagerRankView mImpl) {
        this.mContext = mContext;
        this.mImpl = mImpl;
    }


    public void getdata(String team_id) {
        NetRequest.getInstance().get(mContext, NI.selectManager(team_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    ListBaseBean<RankPeopleBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<ListBaseBean<RankPeopleBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
                    mImpl.setInfoData(result.getData());
                } else {
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void removeMember(String team_id, String ycUserId) {
        NetRequest.getInstance().post(mContext, NI.removeMember(team_id, ycUserId), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    mImpl.removeMember();
                } else {
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void updateJerseyNo(String team_id, String ycUserId, String xg_num) {
        NetRequest.getInstance().post(mContext, NI.updateJerseyNo(team_id, ycUserId, xg_num), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    mImpl.updateJerseyNo();
                } else {
                    ToastAlone.show(jsonObject.get("msg").toString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void auditJoin(String s, String join_id) {
        NetRequest.getInstance().post(mContext, NI.auditJoin(s, join_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    mImpl.auditJoin();
                } else {
                    ToastAlone.show(jsonObject.get("msg").toString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void handoverManagement(String team_id, String user_id) {
        NetRequest.getInstance().post(mContext, NI.handoverManagement(team_id, user_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    mImpl.handoverManagement();
                } else {
                    ToastAlone.show(jsonObject.get("msg").toString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void updatePlace(String team_id, String ycUserId, String returnStr) {
        NetRequest.getInstance().post(mContext, NI.updatePlace(team_id, ycUserId,returnStr), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    mImpl.updatePlace();
                } else {
                    ToastAlone.show(jsonObject.get("msg").toString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void settingIdentity(String team_id, String ycUserId, String sf_str, String qx_str) {
        NetRequest.getInstance().post(mContext, NI.settingIdentity(team_id, ycUserId,sf_str,qx_str), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    mImpl.settingIdentity();
                } else {
                    ToastAlone.show(jsonObject.get("msg").toString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
