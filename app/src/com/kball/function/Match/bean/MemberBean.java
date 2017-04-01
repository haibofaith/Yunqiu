package com.kball.function.Match.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/7.
 */

public class MemberBean implements Serializable {
    private String nickname;//用户昵称
    private String status;//报名比赛的状态 1 已报名 2 待定 3 请假
    private String user_id;//	用户id
    private String portrait;//用户头像

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
