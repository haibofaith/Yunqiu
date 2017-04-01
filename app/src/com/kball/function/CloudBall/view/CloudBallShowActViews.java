package com.kball.function.CloudBall.view;

import com.kball.bean.BaseListBean;
import com.kball.function.CloudBall.bean.CloudBallShowBaseBean;
import com.kball.function.CloudBall.bean.DuihuiBean;
import com.kball.function.CloudBall.bean.GameBean;
import com.kball.function.CloudBall.bean.RankBaseBean;
import com.kball.function.CloudBall.bean.RankInfoBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.home.bean.ListBaseBean;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/8.
 */

public interface CloudBallShowActViews {
    void setInfoData(ArrayList<DuihuiBean> info,String type);

    void setInfo(BaseBean<RankBaseBean> result, int position);

    void setParticipationGameData(BaseBean result);
    void showLoading();
    void dismissLoading();
}
