package com.kball.function.Match.impls;

import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.VideoBean;
import com.kball.function.home.bean.ListBaseBean;

/**
 * Created by xiaole.wang on 17/3/6.
 */

public interface VideoDetailViews {
    void setListData(BaseBean<BaseListBean<VideoBean>> result);
}
