package com.kball.function.CloudBall.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/9.
 */

public class MatchBean implements Serializable {
    private String portrait;
    private String user_id;

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
