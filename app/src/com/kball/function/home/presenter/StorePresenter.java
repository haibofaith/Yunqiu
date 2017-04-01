package com.kball.function.home.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.function.home.view.StoreImpl;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.PublicUtil;
import com.kball.util.SPUtil;

import java.lang.reflect.Type;

/**
 * Created by user on 2017/3/18.
 */

public class StorePresenter {
    StoreImpl storeImpl;

    public StorePresenter(StoreImpl storeImpl) {
        this.storeImpl = storeImpl;
    }


    ///101.201.145.244:8085/tournament/inte/selectCollectionGameList
//    测试地址：47.93.119.150:2498/league/tournament/inte/selectCollectionGameList
    public void selectCollectionGameList(final Context context,String pageNum,String pageSize){
        NetRequest.getInstance().get(context, NI.selectCollectionGameList(pageNum, pageSize), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<BaseListBean<MatchListBean<MatchGameBean>>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<BaseListBean<MatchListBean<MatchGameBean>>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(), type);
//                            mImpl.toastMsg("登录成功");
                    storeImpl.setStoreData(result);
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
