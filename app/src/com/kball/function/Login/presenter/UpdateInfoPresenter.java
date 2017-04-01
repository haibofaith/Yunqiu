package com.kball.function.Login.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.SelectInfoBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.function.Login.interfaceView.UpdateInfoImpl;
import com.kball.function.home.bean.MyInfoBaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.PublicUtil;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by user on 2017/3/23.
 */

public class UpdateInfoPresenter {
    UpdateInfoImpl updateInfo;

    public UpdateInfoPresenter(UpdateInfoImpl updateInfo) {
        this.updateInfo = updateInfo;
    }

    public void modifyInfo(Context context,
                           String user_id, String nickname, String portrait,
                           String stature, String weight, String sex,
                           String birthday, String veteran, String foot,
                           String position, String province, String city, String label){
        NetRequest.getInstance().post(context, NI.modifyInfo(user_id, nickname, portrait, stature, weight, sex, birthday, veteran, foot, position, province, city, label), new RequestHandler() {
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
//                            mImpl.toastMsg("登录成功");
                    updateInfo.setModifyInfoResult(result);
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

    public void selectUserInfo(final Context context) {
        NetRequest.getInstance().get(
                context,
                NI.selectInfo(), new RequestHandler() {

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(String response) {
                        JsonObject jsonObject = new Gson().fromJson(response,
                                JsonObject.class);

                        if (1200 == jsonObject.get("error_code").getAsInt()) {
                            BaseBean<SelectInfoBean> result;
                            Gson gson = new Gson();
                            Type type = new TypeToken<BaseBean<SelectInfoBean>>() {
                            }.getType();
                            result = gson.fromJson(response.toString(),type);
                            updateInfo.setSelectUserInfoData(result.getData());
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
                    }
                });
    }
}
