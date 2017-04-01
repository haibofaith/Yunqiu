package com.kball.function.Match.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/7.
 */

public class MatchDataBean<T> implements Serializable {
    private String user_id;
    private String nickname;
    private String attribution;
    private String type;
    private String total_number;
    private T assists;

    public T getAssists() {
        return assists;
    }

    public void setAssists(T assists) {
        this.assists = assists;
    }

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

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotal_number() {
        return total_number;
    }

    public void setTotal_number(String total_number) {
        this.total_number = total_number;
    }
}
