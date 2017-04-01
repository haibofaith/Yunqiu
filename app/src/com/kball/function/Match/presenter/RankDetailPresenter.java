package com.kball.function.Match.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.function.Match.bean.RankDetailBean;
import com.kball.function.Match.bean.SelectCrunchiesBean;
import com.kball.function.Match.view.MatchInfoViews;
import com.kball.function.Match.view.RankDetailViews;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/7.
 */

public class RankDetailPresenter {
    RankDetailViews matchListView;

    public RankDetailPresenter(RankDetailViews matchListView) {
        this.matchListView = matchListView;
    }

    public void selectCrunchies(Context context, String league_id) {
        NetRequest.getInstance().get(context, NI.selectCrunchies(league_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<SelectCrunchiesBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<SelectCrunchiesBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
                    matchListView.setSelectCrunchiesData(result);
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
