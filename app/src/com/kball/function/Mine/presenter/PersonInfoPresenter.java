package com.kball.function.Mine.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchTabTwoBean;
import com.kball.function.Mine.Views.PersonInfoImpl;
import com.kball.function.Mine.bean.selectTeamListBean;
import com.kball.function.home.bean.MyInfoBaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.PublicUtil;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by user on 2017/3/14.
 */

public class PersonInfoPresenter {
    PersonInfoImpl personInfo;

    public PersonInfoPresenter(PersonInfoImpl personInfo) {
        this.personInfo = personInfo;
    }

    public void selectUserInfo(final Context context,String user_id) {
        NetRequest.getInstance().get(
                context,
                NI.getMyInfo(user_id), new RequestHandler() {

                    @Override
                    public void onStart() {
                        personInfo.showLoading();
                    }

                    @Override
                    public void onSuccess(String response) {
                        JsonObject jsonObject = new Gson().fromJson(response,
                                JsonObject.class);

                        if (1200 == jsonObject.get("error_code").getAsInt()) {
                            BaseBean<MyInfoBaseBean> result;
                            Gson gson = new Gson();
                            Type type = new TypeToken<BaseBean<MyInfoBaseBean>>() {
                            }.getType();
                            result = gson.fromJson(response.toString(),type);
                            personInfo.setObjData(result.getData());
                        }
                        else if(1504 == jsonObject.get("error_code").getAsInt()){
                            PublicUtil.refreshToken(context);
                        }
                        else {
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
                        personInfo.dismissLoading();
                    }
                });
    }

    public void attention(Context context,String focus){
        NetRequest.getInstance().post(context, NI.attention(focus), new RequestHandler() {
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
                    personInfo.setAttentionData(result);
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

    public void cancelAttention(Context context,String focus){
        NetRequest.getInstance().post(context, NI.cancelAttention(focus), new RequestHandler() {
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
                    personInfo.setCancelAttentionData(result);
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

}
