package com.kball.function.Match.impls;

import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;

/**
 * Created by user on 2017/3/5.
 */

public interface MatchListListener {
    void setData(BaseBean<BaseListBean<MatchListBean<MatchGameBean>>> result);
    void setStatusSelectViewGone();
}
