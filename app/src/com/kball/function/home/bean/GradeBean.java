package com.kball.function.home.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/7.
 */

public class GradeBean implements Serializable {
    private String flat;
    private String lose;
    private String no_entry;
    private String victory;

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getLose() {
        return lose;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

    public String getNo_entry() {
        return no_entry;
    }

    public void setNo_entry(String no_entry) {
        this.no_entry = no_entry;
    }

    public String getVictory() {
        return victory;
    }

    public void setVictory(String victory) {
        this.victory = victory;
    }
}
