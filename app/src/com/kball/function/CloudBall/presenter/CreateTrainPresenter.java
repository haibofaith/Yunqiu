package com.kball.function.CloudBall.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.function.CloudBall.view.CreateTrainImpl;
import com.kball.function.Login.bean.BaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;

import java.lang.reflect.Type;

/**
 * Created by user on 2017/3/15.
 */

public class CreateTrainPresenter {
    private CreateTrainImpl createTrain;

    public CreateTrainPresenter(CreateTrainImpl createTrain) {
        this.createTrain = createTrain;
    }

    public void createTournament1(Context context, String classify, String game_name, String entry_teamA, String
            entry_teamB, String uniform_teamA, String game_time, String continue_time, String apply_end_time, String
            game_system, String game_site, String game_cost, String value_added) {


                NetRequest.getInstance().post(NI.createTournament1(classify, game_name, entry_teamA, entry_teamB,
                uniform_teamA, game_time, continue_time, apply_end_time, game_system, game_site, game_cost,
                value_added), new RequestHandler() {
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
//                            mImpl.toastMsg("登录成功");
                    createTrain.setCreateTournamentData(result);
                } else {
                }
            }

            @Override
            public void onFinish() {

            }
        });

    }
    public void updateMatch(Context context, String classify, String game_name, String entry_teamA, String
            entry_teamB, String uniform_teamA, String game_time, String continue_time, String apply_end_time, String
            game_system, String game_site, String game_cost, String value_added) {


                NetRequest.getInstance().post(NI.updateMatch(classify, game_name, entry_teamA, entry_teamB,
                uniform_teamA, game_time, continue_time, apply_end_time, game_system, game_site, game_cost,
                value_added), new RequestHandler() {
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
//                            mImpl.toastMsg("登录成功");
                    createTrain.setCreateTournamentData(result);
                } else {
                }
            }

            @Override
            public void onFinish() {

            }
        });

    }

    public void createTournament(Context context, String classify, String game_name, String entry_teamA, String
            game_time, String continue_time, String game_site) {
        NetRequest.getInstance().post(NI.createTournament(classify, game_name, entry_teamA, game_time,
                continue_time, game_site), new
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
//                            mImpl.toastMsg("登录成功");
                    createTrain.setCreateTournamentData(result);
                } else {
                }
            }

            @Override
            public void onFinish() {

            }
        });

    }
}
