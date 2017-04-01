package com.kball.function.CloudBall.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.function.CloudBall.bean.ExploitsListBean;
import com.kball.function.CloudBall.bean.ExploitsListRecordBean;
import com.kball.function.CloudBall.bean.ExploitsListRecordChildBean;
import com.kball.function.CloudBall.bean.InvitePlayerBean;
import com.kball.function.CloudBall.bean.SearchBaseBean;
import com.kball.function.CloudBall.bean.SearchPeopleBean;
import com.kball.function.CloudBall.bean.SearchRankBean;
import com.kball.function.CloudBall.bean.SearchTUGList;
import com.kball.function.CloudBall.view.InvitePresenterImpl;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by user on 2017/3/18.
 */

public class InvitePlayerPresenter {
    InvitePresenterImpl invitePresenter;

    public InvitePlayerPresenter(InvitePresenterImpl invitePresenter) {
        this.invitePresenter = invitePresenter;
    }

    public void getRecommendationUser(Context context,String number){
        NetRequest.getInstance().get(context, NI.getRecommendationUser(number), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<ArrayList<InvitePlayerBean>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<ArrayList<InvitePlayerBean>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
                    invitePresenter.setGetRecommendationUserData(result);
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

    public void searchTeamAndUserAndGame(Context context,String name, String type){
        NetRequest.getInstance().get(context, NI.searchTeamAndUserAndGame(name,type), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<SearchTUGList> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<SearchTUGList>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
                    invitePresenter.setSearchTeamAndUserAndGameData(result);
                } else {

                }

            }

            @Override
            public void onFinish() {

            }
        });

    }

}
