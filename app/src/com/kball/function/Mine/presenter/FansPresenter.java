package com.kball.function.Mine.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.bean.BaseToBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Mine.Views.FansView;
import com.kball.function.Mine.bean.FansBean;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.function.home.bean.MyInfoBean;
import com.kball.function.home.bean.MyInfoView;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/1.
 */

public class FansPresenter {
    private Context mContext;
    private FansView mImpl;
    public FansPresenter(Context mContext, FansView mImpl) {
        this.mContext = mContext;
        this.mImpl = mImpl;
    }
    public void getData() {
        NetRequest.getInstance().get(
                mContext,
                NI.getFans("1","100"), new RequestHandler() {

                    @Override
                    public void onStart() {
                        mImpl.showLoading();
                    }

                    @Override
                    public void onSuccess(String response) {
                        JsonObject jsonObject = new Gson().fromJson(response,
                                JsonObject.class);

                        if (1200 == jsonObject.get("error_code").getAsInt()) {
                            BaseBean<BaseToBean<FansBean>> result;
                            Gson gson = new Gson();
                            Type type = new TypeToken<BaseBean<BaseToBean<FansBean>>>() {
                            }.getType();
                            result = gson.fromJson(response,type);
                            mImpl.setObjData(result.getData());
                        } else {
                            ToastAlone.show(jsonObject.get("msg")
                                    .getAsString());
                        }
                    }

                    @Override
                    public void onFinish() {
                        mImpl.dismissLoading();
                    }
                });
    }

}
