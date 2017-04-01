package com.kball.function.CloudBall.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/9.
 */

public class RankInfoBean<T> implements Serializable {

    private String group;
    private ArrayList<T> game;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public ArrayList<T> getGame() {
        return game;
    }

    public void setGame(ArrayList<T> game) {
        this.game = game;
    }
}
