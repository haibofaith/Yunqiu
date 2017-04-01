package com.kball.function.CloudBall.view;

import com.kball.function.CloudBall.bean.AreaBean;
import com.kball.function.CloudBall.bean.CityBean;
import com.kball.function.CloudBall.bean.ProviceBean;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/14.
 */

public interface ProviceView {
    void setProvice(ArrayList<ProviceBean> data);

    void setCity(ArrayList<CityBean> data);

    void setArea(ArrayList<AreaBean> data);
}
