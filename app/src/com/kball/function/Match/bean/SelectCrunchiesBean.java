package com.kball.function.Match.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 2017/3/19.
 */

public class SelectCrunchiesBean implements Serializable {
    private ArrayList<selectCrunchiesItemBean> goal_list;
    private ArrayList<selectCrunchiesItemBean> assist_list;
    private ArrayList<selectCrunchiesItemBean> redCard_list;
    private ArrayList<selectCrunchiesItemBean> yellowCard_list;

    public ArrayList<selectCrunchiesItemBean> getYellowCard_list() {
        return yellowCard_list;
    }

    public void setYellowCard_list(ArrayList<selectCrunchiesItemBean> yellowCard_list) {
        this.yellowCard_list = yellowCard_list;
    }

    public ArrayList<selectCrunchiesItemBean> getGoal_list() {
        return goal_list;
    }

    public void setGoal_list(ArrayList<selectCrunchiesItemBean> goal_list) {
        this.goal_list = goal_list;
    }

    public ArrayList<selectCrunchiesItemBean> getAssist_list() {
        return assist_list;
    }

    public void setAssist_list(ArrayList<selectCrunchiesItemBean> assist_list) {
        this.assist_list = assist_list;
    }

    public ArrayList<selectCrunchiesItemBean> getRedCard_list() {
        return redCard_list;
    }

    public void setRedCard_list(ArrayList<selectCrunchiesItemBean> redCard_list) {
        this.redCard_list = redCard_list;
    }
}
