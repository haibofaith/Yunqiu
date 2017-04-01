package com.kball.function.home.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/7.
 */

public class HonorBean implements Serializable {
    private String honor_msg;
    private String league_icon;
    private String honor_name;

    private String league_id;
    private String user_id;
    private String img_url;

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getHonor_name() {
        return honor_name;
    }

    public void setHonor_name(String honor_name) {
        this.honor_name = honor_name;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getHonor_msg() {
        return honor_msg;
    }

    public void setHonor_msg(String honor_msg) {
        this.honor_msg = honor_msg;
    }

    public String getLeague_icon() {
        return league_icon;
    }

    public void setLeague_icon(String league_icon) {
        this.league_icon = league_icon;
    }

    public String getLeague_id() {
        return league_id;
    }

    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }
}
