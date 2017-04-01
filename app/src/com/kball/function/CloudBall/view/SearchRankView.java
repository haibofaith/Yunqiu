package com.kball.function.CloudBall.view;

import com.kball.function.CloudBall.bean.SearchBaseBean;
import com.kball.function.CloudBall.bean.SearchRankBean;
import com.kball.function.home.bean.RanksTJBean;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/16.
 */

public interface SearchRankView {
    void setInfoData(SearchBaseBean<SearchRankBean> data);

    void setObjData(ArrayList<SearchRankBean> data);
}
