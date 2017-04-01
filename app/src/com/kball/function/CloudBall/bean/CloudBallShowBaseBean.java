package com.kball.function.CloudBall.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/8.
 */

public class CloudBallShowBaseBean<T> implements Serializable {
    private ArrayList<T> info;
    private String type;

    public ArrayList<T> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<T> info) {
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
