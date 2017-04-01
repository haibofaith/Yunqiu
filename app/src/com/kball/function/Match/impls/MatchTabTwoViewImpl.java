package com.kball.function.Match.impls;

import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchTabTwoBean;
import com.kball.function.Match.bean.TabTwoSelectBean;

/**
 * Created by user on 2017/3/6.
 */

public interface MatchTabTwoViewImpl {
    void setListData(BaseBean<BaseListBean<MatchTabTwoBean>> result);

    void setLeagueListScreenData(BaseBean<TabTwoSelectBean> result);
    void showLoading();
    void dismissLoading();
}
