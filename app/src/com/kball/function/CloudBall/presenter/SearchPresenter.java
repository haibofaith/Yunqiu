package com.kball.function.CloudBall.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.function.CloudBall.bean.SearchBaseBean;
import com.kball.function.CloudBall.bean.SearchMacthBean;
import com.kball.function.CloudBall.bean.SearchPeopleBean;
import com.kball.function.CloudBall.bean.SearchRankBean;
import com.kball.function.CloudBall.view.SearchRankView;
import com.kball.function.CloudBall.view.SearchView;
import com.kball.function.Login.bean.BaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/16.
 */

public class SearchPresenter {
    private SearchView mImpl;
    Context mContext;
    private int pageNum =1;
    private int pageSize = 10;
    public SearchPresenter(Context mContext, SearchView mImpl) {
        this.mContext = mContext;
        this.mImpl = mImpl;
    }


    public void getRank(String name,String type) {
        NetRequest.getInstance().get(mContext, NI.searchTeamAndUserAndGame(name,type), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<SearchBaseBean<SearchRankBean>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<SearchBaseBean<SearchRankBean>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
//                            mImpl.toastMsg("登录成功");
                    mImpl.setInfoData(result.getData());
                } else {

                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void getPeople(String searchName, String s) {
        NetRequest.getInstance().get(mContext, NI.searchTeamAndUserAndGame(searchName,s), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<SearchBaseBean<SearchPeopleBean>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<SearchBaseBean<SearchPeopleBean>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
//                            mImpl.toastMsg("登录成功");
                    mImpl.setInfoPeopleData(result.getData());
                } else {

                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void getMatch(String searchName, String s) {
        NetRequest.getInstance().get(mContext, NI.searchTeamAndUserAndGame(searchName,s), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<SearchBaseBean<SearchMacthBean>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<SearchBaseBean<SearchMacthBean>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
//                            mImpl.toastMsg("登录成功");
                    mImpl.setInfoMatchData(result.getData());
                } else {

                }
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
