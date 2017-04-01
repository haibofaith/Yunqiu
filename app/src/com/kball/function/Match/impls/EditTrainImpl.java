package com.kball.function.Match.impls;

import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.GameInfoBean;
import com.kball.function.Match.bean.MemberBean;

/**
 * Created by user on 2017/3/17.
 */

public interface EditTrainImpl {
    void setSelectGameInfoData(BaseBean<GameInfoBean<MemberBean>> result);

    void setUpdateGameData(BaseBean result);
}
