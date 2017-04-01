package com.kball.function.Login.interfaceView;

import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.BaseListDataBean;
import com.kball.function.Login.bean.SelectBoundBean;
import com.kball.function.Login.bean.UserRegisterBean;

/**
 * Created by xiaole.wang on 17/2/28.
 */

public interface LoginView {

    void toastMsg(String msg);

    void showLoading();

    void dismissLoading();

    void setObjData(BaseBean<UserRegisterBean> result);

    void bindPhone();

    void setSelectBoundData(BaseListDataBean<SelectBoundBean> result);
}
