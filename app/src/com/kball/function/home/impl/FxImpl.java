package com.kball.function.home.impl;

import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.BaseListDataBean;
import com.kball.function.Match.bean.VideoBean;
import com.kball.function.home.bean.BannerBean;
import com.kball.function.home.bean.FxListBean;
import com.kball.function.home.bean.ListBaseBean;

/**
 * Created by user on 2017/3/21.
 */

public interface FxImpl {


    void setIndexData(BaseListDataBean<BannerBean> result);

    void setGetVideoData(BaseBean<BaseListBean<VideoBean>> result);
}
