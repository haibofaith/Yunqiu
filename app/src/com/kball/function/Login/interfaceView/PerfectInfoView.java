package com.kball.function.Login.interfaceView;

import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.UserRegisterBean;
import com.kball.function.Mine.bean.URLBean;
import com.kball.function.home.bean.ListBaseBean;
import com.kball.function.home.bean.MyInfoBaseBean;

/**
 * Created by user on 2017/2/28.
 */

public interface PerfectInfoView {

    void setObjData(BaseBean<UserRegisterBean> result);

    void setUrlImg(String result);
}
