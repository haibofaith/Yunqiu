package com.kball.function.Match.view;

import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.LeagueInfoBean;
import com.kball.function.Match.bean.LeagueInfoData;

/**
 * Created by xiaole.wang on 17/3/6.
 */

public interface MatchInfoViews {
    void setListInfoData(BaseBean<LeagueInfoData<LeagueInfoBean>> result);
}
