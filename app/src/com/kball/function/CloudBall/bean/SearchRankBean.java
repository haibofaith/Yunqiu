package com.kball.function.CloudBall.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/16.
 */

public class SearchRankBean implements Serializable {
    private String area;
    private String badge;
    private String city;
    private String province;
    private String team_id;
    private String team_name;
    private String total_member;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public String getTotal_member() {
        return total_member;
    }

    public void setTotal_member(String total_member) {
        this.total_member = total_member;
    }
}
