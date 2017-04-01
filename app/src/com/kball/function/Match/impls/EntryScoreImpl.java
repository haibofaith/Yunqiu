package com.kball.function.Match.impls;

import com.kball.function.CloudBall.bean.RankPeopleBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.home.bean.ListBaseBean;


/**
 * Created by user on 2017/3/25.
 */

public interface EntryScoreImpl {
    void setSelectTeamMemberData(ListBaseBean<RankPeopleBean> result);

    void setEnterGrandData(BaseBean result);
}
