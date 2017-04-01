package com.kball.function.CloudBall.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.function.CloudBall.bean.RankPeopleBean;
import com.kball.function.CloudBall.bean.TeamIdBean;
import com.kball.function.CloudBall.view.BallPlayerImpl;
import com.kball.function.CloudBall.view.CreatRankView;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.SPUtil;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/14.
 */

public class CreatRanPresenter {
    private CreatRankView mImpl;
    Context mContext;
    public CreatRanPresenter(Context mContext, CreatRankView mImpl) {
        this.mContext = mContext;
        this.mImpl = mImpl;
    }


    public void insertTeam(String imageURL, String mRankName, String mTypeName, String proviceName, String cityName, String areaName, String mPlace, String mTime, String mTag, String bgImgURL) {
        NetRequest.getInstance().post(mContext, NI.insertTeam(imageURL,mRankName,mTypeName,proviceName,cityName,
                areaName,mPlace,mTime,mTag,bgImgURL), new
                RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<TeamIdBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<TeamIdBean>>() {
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

    public void updateTeamInfo(String team_id, String imageURL, String mRankName, String mTypeName, String proviceName, String cityName, String areaName, String mPlace, String mTime, String mTag, String bgImgURL) {

        NetRequest.getInstance().post(mContext, NI.updateTeamInfo(team_id,imageURL,mRankName,mTypeName,proviceName,cityName,
                areaName,mPlace,mTime,mTag,bgImgURL), new
                RequestHandler() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(String response) {
                        JsonObject jsonObject = new Gson().fromJson(response,
                                JsonObject.class);
                        if (1200 == jsonObject.get("error_code").getAsInt()) {
                            mImpl.setupdate();
                        } else {
                        }
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }
}
