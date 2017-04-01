package com.kball.function.Match.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/8.
 */

public class MatchIntegralBean<T> implements Serializable {
    private ArrayList<T> detailed;
    private String group;
    private String league_type;

    public ArrayList<T> getDetailed() {
        return detailed;
    }

    public void setDetailed(ArrayList<T> detailed) {
        this.detailed = detailed;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getLeague_type() {
        return league_type;
    }

    public void setLeague_type(String league_type) {
        this.league_type = league_type;
    }
}
