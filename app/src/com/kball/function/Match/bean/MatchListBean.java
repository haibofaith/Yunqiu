package com.kball.function.Match.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 2017/3/5.
 */

public class MatchListBean<T> implements Serializable {
    private String groups;
    private ArrayList<T> game;
    private String group;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public ArrayList<T> getGame() {
        return game;
    }

    public void setGame(ArrayList<T> game) {
        this.game = game;
    }
}
