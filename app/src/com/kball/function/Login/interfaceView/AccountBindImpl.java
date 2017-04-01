package com.kball.function.Login.interfaceView;

import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.BaseListDataBean;
import com.kball.function.Login.bean.SelectBoundBean;

/**
 * Created by user on 2017/3/26.
 */

public interface AccountBindImpl {
    void setSelectBoundData(BaseListDataBean<SelectBoundBean> result);

    void setBoundData(BaseBean result);

    void setUnwrapData(BaseBean result);
}
