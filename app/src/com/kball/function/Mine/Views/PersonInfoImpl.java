package com.kball.function.Mine.Views;

import com.kball.bean.BaseListBean;
import com.kball.function.Login.bean.BaseBean;
import com.kball.function.Match.bean.MatchTabTwoBean;
import com.kball.function.Mine.bean.selectTeamListBean;
import com.kball.function.home.bean.MyInfoBaseBean;

import java.util.ArrayList;

/**
 * Created by user on 2017/3/14.
 */

public interface PersonInfoImpl {
    void showLoading();
    void dismissLoading();
    void setObjData(MyInfoBaseBean data);



    void setAttentionData(BaseBean result);

    void setCancelAttentionData(BaseBean result);
}
