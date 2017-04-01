package com.kball.function.Mine.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Mine.Views.SelectTeamImpl;
import com.kball.function.Mine.bean.selectTeamListBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by user on 2017/3/15.
 */

public class SelectTeamPresenter {
    SelectTeamImpl selectTeam;

    public SelectTeamPresenter(SelectTeamImpl selectTeam) {
        this.selectTeam = selectTeam;
    }

    public void selectTeamList(Context context){
        NetRequest.getInstance().get(context, NI.selectTeamList(), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<ArrayList<selectTeamListBean>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<ArrayList<selectTeamListBean>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
                    selectTeam.setSelectTeamListData(result);
                } else {
                    ToastAlone.show(jsonObject.get("msg")
                            .getAsString());
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
