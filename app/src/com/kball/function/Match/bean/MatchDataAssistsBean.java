package com.kball.function.Match.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/7.
 */

public class MatchDataAssistsBean implements Serializable {
    private String user_id;
    private String nickname;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
