package com.kball.function.Match.impls;

import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.GameFiltrateConditionsBean;
import com.kball.function.Match.bean.MatchGameBean;
import com.kball.function.Match.bean.MatchListBean;

/**
 * Created by user on 2017/3/4.
 */

public interface MatchProgramView {

    void setObjData(BaseBean<GameFiltrateConditionsBean> result);

    void dis();

    void show();
}
