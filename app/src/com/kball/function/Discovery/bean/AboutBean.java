package com.kball.function.Discovery.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 2017/3/24.
 */

public class AboutBean implements Serializable {
    private ArrayList<SystemBean>  system;
    private ArrayList<TimeBean>  time;

    public ArrayList<SystemBean> getSystem() {
        return system;
    }

    public void setSystem(ArrayList<SystemBean> system) {
        this.system = system;
    }

    public ArrayList<TimeBean> getTime() {
        return time;
    }

    public void setTime(ArrayList<TimeBean> time) {
        this.time = time;
    }
}
