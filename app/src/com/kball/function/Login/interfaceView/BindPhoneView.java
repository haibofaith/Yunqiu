package com.kball.function.Login.interfaceView;

import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.UserRegisterBean;

/**
 * Created by user on 2017/3/3.
 */

public interface BindPhoneView {
    void setBoundResult(BaseBean<UserRegisterBean> result);

    void setVerifycode(BaseBean result);
}
