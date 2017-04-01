package com.kball.function.CloudBall.view;

import com.kball.function.CloudBall.bean.RankPeopleBean;

import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/18.
 */

public interface ManagerRankView {
    void setInfoData(ArrayList<RankPeopleBean> data);

    void removeMember();

    void updateJerseyNo();

    void auditJoin();

    void handoverManagement();

    void updatePlace();

    void settingIdentity();
}
