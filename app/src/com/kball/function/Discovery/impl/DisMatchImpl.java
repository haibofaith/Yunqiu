package com.kball.function.Discovery.impl;

import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchTabTwoBean;
import com.kball.function.Match.bean.TabTwoSelectBean;

/**
 * Created by user on 2017/3/21.
 */

public interface DisMatchImpl {
    void setMeTeamLeagueListData(BaseBean<BaseListBean<MatchTabTwoBean>> result);

    void setLeagueListScreenData(BaseBean<TabTwoSelectBean> result);
}