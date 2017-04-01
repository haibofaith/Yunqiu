package com.kball.function.Match.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.GameInfoBean;
import com.kball.function.Match.bean.MemberBean;
import com.kball.function.Match.impls.EditTrainImpl;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by user on 2017/3/17.
 */

public class EditTrainPresenter {
    EditTrainImpl editTrain;

    public EditTrainPresenter(EditTrainImpl editTrain) {
        this.editTrain = editTrain;
    }

    public void selectGameInfo(Context context, String game_id) {
        NetRequest.getInstance().get(context, NI.selectGameInfo(game_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<GameInfoBean<MemberBean>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<GameInfoBean<MemberBean>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
//                            mImpl.toastMsg("登录成功");
                    editTrain.setSelectGameInfoData(result);
                } else {
                    ToastAlone.show(jsonObject.get("msg").getAsString());
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void updateGame(Context context,String classify,String game_name, String entry_teamA, String
            game_time, String continue_time, String game_site,String game_id) {
        NetRequest.getInstance().post(context, NI.updateGame1(classify,game_name,entry_teamA,game_time,
                continue_time,game_site,game_id), new RequestHandler() {
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
                    editTrain.setUpdateGameData(result);
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
