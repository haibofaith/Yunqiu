package com.kball.function.Match.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.IntegralDetailBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchIntegralBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.function.Match.view.IntegralDetailViews;
import com.kball.function.Match.view.MatchDetailViews;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/6.
 */

public class IntegralDetailPresenter {

    IntegralDetailViews matchListView;

    public IntegralDetailPresenter(IntegralDetailViews matchListView) {
        this.matchListView = matchListView;
    }

    public void getVideo(Context context, String league_id){
        NetRequest.getInstance().get(context, NI.getjifen(league_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    ListBaseBean<MatchIntegralBean<IntegralDetailBean>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<ListBaseBean<MatchIntegralBean<IntegralDetailBean>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
                    matchListView.setListData(result.getData());
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
