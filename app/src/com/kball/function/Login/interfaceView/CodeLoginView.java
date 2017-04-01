package com.kball.function.Login.interfaceView;

import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.UserRegisterBean;

/**
 * Created by user on 2017/2/28.
 */

public interface CodeLoginView {
    void setVerifycode(BaseBean result);
    void setObjData(BaseBean<UserRegisterBean> result);
}
