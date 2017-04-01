package com.kball.function.home.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.C;
import com.kball.bean.BaseToBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.function.home.bean.RanksTJBean;
import com.kball.function.home.ui.HomeYqFragment1;
import com.kball.function.home.view.BallRanksView;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.SPUtil;
import com.kball.util.ToastAlone;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/1.
 */

public class BallRanksPresenter {
    private Context mContext;
    private BallRanksView mImpl;
    private ImageLoader imageLoader;
    public BallRanksPresenter(Context mContext, BallRanksView mImpl,ImageLoader imageLoader) {
        this.mContext = mContext;
        this.mImpl = mImpl;
        this.imageLoader = imageLoader;
    }

    public void getData() {
        NetRequest.getInstance().get(
                mContext,
                NI.getTJ(), new RequestHandler() {

                    @Override
                    public void onStart() {
                        mImpl.showLoading1();
                    }

                    @Override
                    public void onSuccess(String response) {
                        JsonObject jsonObject = new Gson().fromJson(response,
                                JsonObject.class);

                        if (1200 == jsonObject.get("error_code").getAsInt()) {
                            ListBaseBean<RanksTJBean> result;
                            Gson gson = new Gson();
                            Type type = new TypeToken<ListBaseBean<RanksTJBean>>() {
                            }.getType();
                            result = gson.fromJson(response.toString(),type);
//                            mImpl.toastMsg("登录成功");
                            mImpl.setObjData(result.getData());
                        } else {
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
