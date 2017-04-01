package com.kball.function.Match.ui;

import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchAboutDataBean;
import com.kball.function.Match.bean.MatchDataAssistsBean;
import com.kball.function.Match.bean.MatchDataBean;

/**
 * Created by xiaole.wang on 17/3/6.
 */

public interface MatchAboutDataViews {
    void setMatchAboutData(BaseBean<MatchAboutDataBean<MatchDataBean<MatchDataAssistsBean>>> result);
}
