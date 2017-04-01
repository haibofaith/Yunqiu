package com.kball.function.Match.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/5.
 */

public class MatchSelectListBean implements Serializable {
    private String name;
    private String value;

    public MatchSelectListBean(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
