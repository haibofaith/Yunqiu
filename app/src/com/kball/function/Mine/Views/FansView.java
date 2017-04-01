package com.kball.function.Mine.Views;

import com.kball.bean.BaseToBean;
import com.kball.function.Mine.bean.FansBean;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/1.
 */

public interface FansView {
    void showLoading();

    void dismissLoading();



    void setObjData(BaseToBean<FansBean> data);

    void setqdData(BaseToBean<FansBean> data);
}
