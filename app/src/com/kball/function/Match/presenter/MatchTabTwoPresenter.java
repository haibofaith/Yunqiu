package com.kball.function.Match.presenter;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchTabTwoBean;
import com.kball.function.Match.bean.TabTwoSelectBean;
import com.kball.function.Match.impls.MatchTabTwoViewImpl;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by user on 2017/3/6.
 */

public class MatchTabTwoPresenter {
    MatchTabTwoViewImpl matchTabTwoViewImpl;

    public MatchTabTwoPresenter(MatchTabTwoViewImpl matchTabTwoViewImpl) {
        this.matchTabTwoViewImpl = matchTabTwoViewImpl;
    }

    public void meTeamLeagueList(Context context, String pageNum, String pageSize, String team_id, String status) {
        NetRequest.getInstance().get(context, NI.meTeamLeagueList(pageNum, pageSize, team_id, status), new
                RequestHandler() {
            @Override
            public void onStart() {
                matchTabTwoViewImpl.showLoading();
            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<BaseListBean<MatchTabTwoBean>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<BaseListBean<MatchTabTwoBean>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
                    matchTabTwoViewImpl.setListData(result);
                } else {
                    ToastAlone.show(jsonObject.get("msg").getAsString());
                    if ("缺少用户id跟token".equals(jsonObject.get("msg").getAsString())) {
                        SPUtil.getInstance().putString(C.SP.USER_ID, "");
                        SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN, "");
                    }
                }
            }

            @Override
            public void onFinish() {
                matchTabTwoViewImpl.dismissLoading();
            }
        });
    }

    public void leagueListScreen(Context context) {
        NetRequest.getInstance().get(context, NI.leagueListScreen(), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<TabTwoSelectBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<TabTwoSelectBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
                    matchTabTwoViewImpl.setLeagueListScreenData(result);
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
