package com.kball.function.Match.view;

import com.kball.function.Match.bean.IntegralDetailBean;
import com.kball.function.Match.bean.MatchIntegralBean;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/6.
 */

public interface IntegralDetailViews {
    void setListData(ArrayList<MatchIntegralBean<IntegralDetailBean>> data);
}
