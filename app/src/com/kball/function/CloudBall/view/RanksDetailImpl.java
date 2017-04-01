package com.kball.function.CloudBall.view;

import com.kball.function.Login.bean.BaseBean;
import com.kball.function.home.bean.RanksBaseBean;

/**
 * Created by xiaole.wang on 17/3/7.
 */

public interface RanksDetailImpl {
    void setListInfoData(BaseBean<RanksBaseBean> result);

    void setInvite();

    void applyJoinTeam();

    void dissolveTeam();

    void attentionTeam();

    void cancelAttentionTeam();

    void applyJoinTeamByInvite();

    void auditInvitation();
}
