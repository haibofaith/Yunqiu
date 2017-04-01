package com.kball.function.home.view;

import com.kball.function.home.bean.RanksTJBean;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/1.
 */

public interface BallRanksView {

    void setObjData(ArrayList<RanksTJBean> info);

    void showLoading1();

    void dismissLoading1();
}
