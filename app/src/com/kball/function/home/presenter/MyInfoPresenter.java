package com.kball.function.home.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.bean.BaseToBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.function.home.bean.MyInfoBaseBean;
import com.kball.function.home.bean.MyInfoBean;
import com.kball.function.home.bean.MyInfoView;
import com.kball.function.home.bean.RanksTJBean;
import com.kball.function.home.view.BallRanksView;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.PublicUtil;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/1.
 */

public class MyInfoPresenter  {
    private Context mContext;
    private MyInfoView mImpl;
    public MyInfoPresenter(Context mContext, MyInfoView mImpl) {
        this.mContext = mContext;
        this.mImpl = mImpl;
    }

    public void getData() {
        NetRequest.getInstance().get(
                mContext,
                NI.getMyInfo(), new RequestHandler() {

                    @Override
                    public void onStart() {
                        mImpl.showLoading1();
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
//                            mImpl.toastMsg("登录成功");
                            mImpl.setObjData(result.getData());
                        }
                        else if(1504 == jsonObject.get("error_code").getAsInt()){
                            PublicUtil.refreshToken(mContext);
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
                        mImpl.dismissLoading1();
                    }
                });
    }
}
