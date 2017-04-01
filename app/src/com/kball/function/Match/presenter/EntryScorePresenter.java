package com.kball.function.Match.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.function.CloudBall.bean.RankPeopleBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.impls.EntryScoreImpl;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.SPUtil;

import java.lang.reflect.Type;

/**
 * Created by user on 2017/3/25.
 */

public class EntryScorePresenter {
    EntryScoreImpl entryScore;

    public EntryScorePresenter(EntryScoreImpl entryScore) {
        this.entryScore = entryScore;
    }

    public void selectTeamMember(Context context,String team_id){
        NetRequest.getInstance().get(context, NI.selectTeamMember(team_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    ListBaseBean<RankPeopleBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<ListBaseBean<RankPeopleBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
//                            mImpl.toastMsg("登录成功");
                    entryScore.setSelectTeamMemberData(result);
                } else {
                    if ("缺少用户id跟token".equals(jsonObject.get("msg").getAsString())){
                        SPUtil.getInstance().putString(C.SP.USER_ID,"");
                        SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN,"");
                    }
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void enterGrand(Context context,String data_json){
        NetRequest.getInstance().post(context, NI.enterGrand(data_json), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
                    entryScore.setEnterGrandData(result);
                } else {
                    if ("缺少用户id跟token".equals(jsonObject.get("msg").getAsString())){
                        SPUtil.getInstance().putString(C.SP.USER_ID,"");
                        SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN,"");
                    }
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

}
