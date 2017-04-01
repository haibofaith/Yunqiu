package com.kball.function.Match.views;

import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;

/**
 * Created by user on 2017/3/9.
 */
public interface TeamSelectListener {
    void setTeamSelectData(BaseBean<BaseListBean<MatchListBean<MatchGameBean>>> result);
}
