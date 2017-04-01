package com.kball.function.CloudBall.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.function.CloudBall.bean.RankPeopleBean;
import com.kball.function.CloudBall.bean.SearchBaseBean;
import com.kball.function.CloudBall.bean.SearchRankBean;
import com.kball.function.CloudBall.view.BallPlayerImpl;
import com.kball.function.CloudBall.view.SearchRankView;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.function.home.bean.RanksTJBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/16.
 */

public class SearchRankPresenter {
    private SearchRankView mImpl;
    Context mContext;
    private int pageNum = 1;
    private int pageSize = 10;

    public SearchRankPresenter(Context mContext, SearchRankView mImpl) {
        this.mContext = mContext;
        this.mImpl = mImpl;
    }


    public void getData() {
        NetRequest.getInstance().get(mContext, NI.getTJ1("20"), new RequestHandler() {

            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);

                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    ListBaseBean<SearchRankBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<ListBaseBean<SearchRankBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
//                            mImpl.toastMsg("登录成功");
                    mImpl.setObjData(result.getData());
                } else {
                    ToastAlone.show(jsonObject.get("msg").getAsString());
                }
            }

            @Override
            public void onFinish() {
            }
        });
    }

    public void getdata(String name, String type) {
        NetRequest.getInstance().get(mContext, NI.searchTeamAndUserAndGame(name, type), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<SearchBaseBean<SearchRankBean>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<SearchBaseBean<SearchRankBean>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
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
}
