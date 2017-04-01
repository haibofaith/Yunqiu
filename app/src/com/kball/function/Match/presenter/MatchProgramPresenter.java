package com.kball.function.Match.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.function.Match.bean.GameFiltrateConditionsBean;
import com.kball.function.Match.impls.MatchProgramView;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.PublicUtil;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by user on 2017/3/4.
 */

public class MatchProgramPresenter {
    MatchProgramView matchProgramView;

    public MatchProgramPresenter(MatchProgramView matchProgramView) {
        this.matchProgramView = matchProgramView;
    }

    public void gameFiltrateConditions(final Context context){
        NetRequest.getInstance().get(context, NI.gameFiltrateConditions(), new RequestHandler() {
            @Override
            public void onStart() {
                matchProgramView.show();
            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<GameFiltrateConditionsBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<GameFiltrateConditionsBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
//                            mImpl.toastMsg("登录成功");
                    matchProgramView.setObjData(result);
                }
                else if(1504 == jsonObject.get("error_code").getAsInt()){
                    PublicUtil.refreshToken(context);
                }
                else {
//                    ToastAlone.show(jsonObject.get("msg")
//                            .getAsString());
                    if ("缺少用户id跟token".equals(jsonObject.get("msg").getAsString())){
                        SPUtil.getInstance().putString(C.SP.USER_ID,"");
                        SPUtil.getInstance().putString(C.SP.ACCESS_TOKEN,"");
                    }
                }

            }

            @Override
            public void onFinish() {
                matchProgramView.dis();
            }
        });
    }


}
