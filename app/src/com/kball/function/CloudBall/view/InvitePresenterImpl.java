package com.kball.function.CloudBall.view;

import com.kball.function.CloudBall.bean.InvitePlayerBean;
import com.kball.function.CloudBall.bean.SearchBaseBean;
import com.kball.function.CloudBall.bean.SearchPeopleBean;
import com.kball.function.CloudBall.bean.SearchTUGList;
import com.kball.function.Login.bean.BaseBean;

import java.util.ArrayList;

/**
 * Created by user on 2017/3/18.
 */

public interface InvitePresenterImpl {
    void setGetRecommendationUserData(BaseBean<ArrayList<InvitePlayerBean>> result);

    void setSearchTeamAndUserAndGameData(BaseBean<SearchTUGList> result);
}
