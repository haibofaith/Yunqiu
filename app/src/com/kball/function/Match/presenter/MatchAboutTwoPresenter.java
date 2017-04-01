package com.kball.function.Match.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;
import com.kball.function.Match.bean.VideoBean;
import com.kball.function.Match.impls.MatchAboutTwoViews;
import com.kball.function.Match.view.MatchDetailViews;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.net.NI;
import com.kball.net.NetRequest;
import com.kball.net.RequestHandler;
import com.kball.util.ToastAlone;

import java.lang.reflect.Type;

/**
 * Created by xiaole.wang on 17/3/6.
 */

public class MatchAboutTwoPresenter  {
    MatchAboutTwoViews matchListView;

    public MatchAboutTwoPresenter(MatchAboutTwoViews matchListView) {
        this.matchListView = matchListView;
    }

    public void getVideo(Context context, String game_id){
        //分类 1：赛事 2：比赛 3：精彩视频
        NetRequest.getInstance().get(context, NI.getVideo("2",game_id), new RequestHandler() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(String response) {
                JsonObject jsonObject = new Gson().fromJson(response,
                        JsonObject.class);
                if (1200 == jsonObject.get("error_code").getAsInt()) {
                    BaseBean<BaseListBean<VideoBean>> result;
                    Gson gson = new Gson();
                    Type type = new TypeToken<BaseBean<BaseListBean<VideoBean>>>() {
                    }.getType();
                    result = gson.fromJson(response.toString(),type);
//                            mImpl.toastMsg("登录成功");
                    matchListView.setListData(result.getData().getList());
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
