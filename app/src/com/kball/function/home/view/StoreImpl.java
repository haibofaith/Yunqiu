package com.kball.function.home.view;

import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;

/**
 * Created by user on 2017/3/18.
 */

public interface StoreImpl {

    void setStoreData(BaseBean<BaseListBean<MatchListBean<MatchGameBean>>> result);
}
