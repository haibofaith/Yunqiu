package com.kball.function.Mine.Views;

import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Mine.bean.selectTeamListBean;

import java.util.ArrayList;

/**
 * Created by user on 2017/3/15.
 */

public interface SelectTeamImpl {
    void setSelectTeamListData(BaseBean<ArrayList<selectTeamListBean>> result);
}
