package com.kball.function.CloudBall.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.function.CloudBall.bean.GetTeamPowerBean;
import com.kball.function.CloudBall.bean.SearchRankBean;
import com.kball.function.CloudBall.view.TeamCapaImpl;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by user on 2017/3/23.
 */

public class TeamCapaPresenter {
    TeamCapaImpl teamCapa;

    public TeamCapaPresenter(TeamCapaImpl teamCapa) {
        this.teamCapa = teamCapa;
    }

    public void getTeamPower(Context context,String team_id){
        NetRequest.getInstance().get(context, NI.getTeamPower(team_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);

                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    ListBaseBean<GetTeamPowerBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<ListBaseBean<GetTeamPowerBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
                    teamCapa.setGetTeamPowerData(result);
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
