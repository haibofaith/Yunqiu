package com.kball.function.Match.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 2017/3/4.
 */

public class GameFiltrateConditionsBean implements Serializable{
    private ArrayList<StatusBean> status;
    private ArrayList<TimeBean> time;
    private ArrayList<TeamBean> team;

    public ArrayList<StatusBean> getStatus() {
        return status;
    }

    public void setStatus(ArrayList<StatusBean> status) {
        this.status = status;
    }

    public ArrayList<TimeBean> getTime() {
        return time;
    }

    public void setTime(ArrayList<TimeBean> time) {
        this.time = time;
    }

    public ArrayList<TeamBean> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<TeamBean> team) {
        this.team = team;
    }
}
