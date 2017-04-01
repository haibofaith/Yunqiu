package com.kball.function.CloudBall.view;

import com.kball.function.CloudBall.bean.SearchBaseBean;
import com.kball.function.CloudBall.bean.SearchMacthBean;
import com.kball.function.CloudBall.bean.SearchPeopleBean;
import com.kball.function.CloudBall.bean.SearchRankBean;

/**
 * Created by xiaole.wang on 17/3/16.
 */

public interface SearchView {
    void setInfoData(SearchBaseBean<SearchRankBean> data);

    void setInfoPeopleData(SearchBaseBean<SearchPeopleBean> data);

    void setInfoMatchData(SearchBaseBean data);
}
