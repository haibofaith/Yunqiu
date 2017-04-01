package com.kball.function.Match.view;

import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchDetailViewBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;

/**
 * Created by xiaole.wang on 17/3/6.
 */

public interface MatchDetailViews  {

    void setListData(BaseListBean<MatchListBean<MatchDetailViewBean>> result);
}
