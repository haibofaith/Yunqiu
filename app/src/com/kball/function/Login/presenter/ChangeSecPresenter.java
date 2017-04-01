package com.kball.function.Login.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.interfaceView.ChangeSecView;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by user on 2017/3/13.
 */

public class ChangeSecPresenter {
    ChangeSecView changeSecView;

    public ChangeSecPresenter(ChangeSecView changeSecView) {
        this.changeSecView = changeSecView;
    }

    //1：修改密码，原始密码不可为null，2：创建密码，原始密码传null
    public void updatePassword(Context context,String worn_password, String new_password, String type){
        NetRequest.getInstance().post(context, NI.updatePassword(worn_password,new_password,type), new RequestHandler() {
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
                    changeSecView.setChangeSecData(result);
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
