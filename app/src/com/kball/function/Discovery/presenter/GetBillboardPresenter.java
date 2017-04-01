package com.kball.function.Discovery.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.bean.BaseListBean;
import com.kball.function.Discovery.bean.GetBillboardBean;
import com.kball.function.Discovery.impl.GetBillboardImpl;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.BaseListDataBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.PublicUtil;
import com.kball.util.SPUtil;

import java.lang.reflect.Type;

/**
 * Created by user on 2017/3/21.
 */

public class GetBillboardPresenter {
    GetBillboardImpl getBillboard;

    public GetBillboardPresenter(GetBillboardImpl getBillboard) {
        this.getBillboard = getBillboard;
    }

    public void getBillboard(final Context context, String type){
        NetRequest.getInstance().get(context, NI.getBillboard(type), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseListDataBean<GetBillboardBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseListDataBean<GetBillboardBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
//                            mImpl.toastMsg("登录成功");
                    getBillboard.setGetBillboardData(result);
                } else if (1504 == jsonObject.get("error_code").getAsInt()) {
                    PublicUtil.refreshToken(context);
                } else {
//                        ToastAlone.show(jsonObject.get("msg").getAsString());
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
