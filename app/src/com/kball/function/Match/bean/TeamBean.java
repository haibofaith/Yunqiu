package com.kball.function.Match.bean;

/**
 * Created by user on 2017/3/4.
 */

public class TeamBean {
    private String team_id;
    private String team_name;

    public TeamBean(String team_id, String team_name) {
        this.team_id = team_id;
        this.team_name = team_name;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }
}
