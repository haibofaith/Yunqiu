package com.kball.function.Discovery.impl;

import com.kball.bean.BaseListBean;
import com.kball.function.Discovery.bean.GetBillboardBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.BaseListDataBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;

/**
 * Created by user on 2017/3/21.
 */

public interface GetBillboardImpl {
    void setGetBillboardData(BaseListDataBean<GetBillboardBean> result);
}
