package com.kball.function.CloudBall.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/2/17.
 */

public class CloudBallPeopleBean implements Serializable {
    private boolean isShow;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
