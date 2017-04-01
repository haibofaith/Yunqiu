package com.kball.function.Match.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/25.
 */

public class GoalBean implements Serializable {
    private String team_id;
    private String user_id;

    public GoalBean(String team_id, String user_id) {
        this.team_id = team_id;
        this.user_id = user_id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
