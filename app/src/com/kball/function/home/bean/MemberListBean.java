package com.kball.function.home.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/7.
 */

public class MemberListBean implements Serializable{
    private String identity;
    private String jersey_no;
    private String nickname;
    private String place;
    private String portrait;
    private String status;
    private String user_id;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getJersey_no() {
        return jersey_no;
    }

    public void setJersey_no(String jersey_no) {
        this.jersey_no = jersey_no;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
