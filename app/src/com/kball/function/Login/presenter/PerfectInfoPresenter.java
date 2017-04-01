package com.kball.function.Login.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.function.Login.interfaceView.PerfectInfoView;
import com.kball.function.Mine.bean.URLBean;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.function.home.bean.MyInfoBaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.PublicUtil;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;
import com.kball.util.UpYunUtil;
import com.upyun.library.listener.UpCompleteListener;

import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Type;

/**
 * Created by user on 2017/2/28.
 */

public class PerfectInfoPresenter {
    PerfectInfoView perfectInfoView;

    public PerfectInfoPresenter(PerfectInfoView perfectInfoView) {
        this.perfectInfoView = perfectInfoView;
    }


    public void perfectInfo(Context context,String user_id,String credential,String nickname,String portrait) {
        NetRequest.getInstance().post(context, NI.perfectInfo(user_id, credential, nickname, portrait), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);

                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<UserRegisterBean> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<UserRegisterBean>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
                    perfectInfoView.setObjData(result);
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

    public void uploadFile(Context context,File mBitFile) {
        UpYunUtil.upYunImg(mBitFile, new UpCompleteListener() {
            @Override
            public void onComplete(boolean isSuccess, String result) {
                JsonObject jsonObject = new Gson().fromJson(result,
                        JsonObject.class);
                if (200==jsonObject.get("code").getAsInt()){
                    perfectInfoView.setUrlImg(jsonObject.get("url").getAsString());
                }
            }
        });
    }

}
