package com.kball.function.Mine.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/10.
 */

public class URLBean implements Serializable {
    private String url[];

    public String[] getUrl() {
        return url;
    }

    public void setUrl(String[] url) {
        this.url = url;
    }
}
