package com.kball.function.Match.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.LeagueInfoBean;
import com.kball.function.Match.bean.LeagueInfoData;
import com.kball.function.Match.view.MatchDetailViews;
import com.kball.function.Match.view.MatchInfoViews;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/6.
 */

public class MatchInfoPresenter {
    MatchInfoViews matchListView;

    public MatchInfoPresenter(MatchInfoViews matchListView) {
        this.matchListView = matchListView;
    }

    public void getMatchInfo(Context context, String league_id) {
        NetRequest.getInstance().get(context, NI.getMatchInfo(league_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<LeagueInfoData<LeagueInfoBean>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<LeagueInfoData<LeagueInfoBean>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
//                            mImpl.toastMsg("登录成功");
                    matchListView.setListInfoData(result);
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
