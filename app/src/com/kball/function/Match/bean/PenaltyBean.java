package com.kball.function.Match.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/25.
 */

public class PenaltyBean implements Serializable {
    private String team_id;
    private String number;

    public PenaltyBean(String team_id, String number) {
        this.team_id = team_id;
        this.number = number;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
