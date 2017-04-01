package com.kball.function.CloudBall.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.bean.BaseListBean;
import com.kball.function.CloudBall.bean.MessageBean;
import com.kball.function.CloudBall.bean.MessageRanksBean;
import com.kball.function.CloudBall.view.ChildMessageView;
import com.kball.function.CloudBall.view.MessageView;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/26.
 */

public class ChildMessagePresenter {
    ChildMessageView teamCapa;

    public ChildMessagePresenter(ChildMessageView teamCapa) {
        this.teamCapa = teamCapa;
    }

    public void getMessage(Context context,String type,String typeid){
        NetRequest.getInstance().get(context, NI.getMessage(type,typeid,"1","100"), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);

                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<BaseListBean<MessageRanksBean>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<ListBaseBean<MessageBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
                    teamCapa.setdata(result.getData().getList());
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
