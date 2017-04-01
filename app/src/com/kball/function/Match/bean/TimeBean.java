package com.kball.function.Match.bean;

/**
 * Created by user on 2017/3/4.
 */

public class TimeBean {
    private String time_name;
    private String time_value;

    public TimeBean(String time_value,String time_name) {
        this.time_name = time_name;
        this.time_value = time_value;
    }

    public String getTime_value() {
        return time_value;
    }

    public void setTime_value(String time_value) {
        this.time_value = time_value;
    }

    public String getTime_name() {
        return time_name;
    }

    public void setTime_name(String time_name) {
        this.time_name = time_name;
    }
}
