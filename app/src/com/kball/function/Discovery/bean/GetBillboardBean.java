package com.kball.function.Discovery.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/21.
 */

public class GetBillboardBean implements Serializable {
    private String user_id;
    private String nickname;
    private String portrait;
    private String power;
    private String team_id;
    private String team_name;
    private String badge;
    private String mean_power;

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

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getMean_power() {
        return mean_power;
    }

    public void setMean_power(String mean_power) {
        this.mean_power = mean_power;
    }
}
