package com.kball.function.Match.impls;

import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Login.bean.GameInfoBean;
import com.kball.function.Match.bean.MemberBean;

/**
 * Created by xiaole.wang on 17/3/6.
 */

public interface MatchAboutViews  {
    void setData(BaseBean<GameInfoBean<MemberBean>> result);

    void setAuditOrFightGameData(BaseBean result);

    void setParticipationGameData(BaseBean result);

    void cancleGame();
}
