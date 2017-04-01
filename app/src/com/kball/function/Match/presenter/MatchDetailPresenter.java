package com.kball.function.Match.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchDetailViewBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.function.Match.impls.MatchListView;
import com.kball.function.Match.view.MatchDetailViews;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/6.
 */

public class MatchDetailPresenter {
    MatchDetailViews matchDetailViews;

    public MatchDetailPresenter(MatchDetailViews matchListView) {
        this.matchDetailViews = matchListView;
    }

    public void match_saichen(Context context, String league_id, int  i){
        NetRequest.getInstance().get(context, NI.match_saichen(league_id,i), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<BaseListBean<MatchListBean<MatchDetailViewBean>>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<BaseListBean<MatchListBean<MatchDetailViewBean>>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
                    matchDetailViews.setListData(result.getData());
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
