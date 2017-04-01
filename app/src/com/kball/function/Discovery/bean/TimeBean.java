package com.kball.function.Discovery.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/24.
 */

public class TimeBean implements Serializable {
    private String time_name;
    private String time_value;

    public String getTime_name() {
        return time_name;
    }

    public void setTime_name(String time_name) {
        this.time_name = time_name;
    }

    public String getTime_value() {
        return time_value;
    }

    public void setTime_value(String time_value) {
        this.time_value = time_value;
    }
}
