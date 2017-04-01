package com.kball.function.Match.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchAboutDataBean;
import com.kball.function.Match.bean.MatchDataAssistsBean;
import com.kball.function.Match.bean.MatchDataBean;
import com.kball.function.Match.ui.MatchAboutDataViews;
import com.kball.function.Match.view.IntegralDetailViews;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/6.
 */

public class MatchAboutDataPresenter {

    MatchAboutDataViews matchListView;

    public MatchAboutDataPresenter(MatchAboutDataViews matchListView) {
        this.matchListView = matchListView;
    }

    public void selectGrand(Context context, String game_id) {
        NetRequest.getInstance().get(context, NI.selectGrand(game_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<MatchAboutDataBean<MatchDataBean<MatchDataAssistsBean>>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<MatchAboutDataBean<MatchDataBean<MatchDataAssistsBean>>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
//                            mImpl.toastMsg("登录成功");
                    matchListView.setMatchAboutData(result);
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