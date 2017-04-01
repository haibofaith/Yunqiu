package com.kball.function.home.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/1.
 */

public class RanksTJBean implements Serializable {
    private String area;
    private String badge;
    private String city;
    private String home;
    private String power;
    private String province;
    private String team_id;
    private String team_name;

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
        if (city.equals("市辖区")){
            return "";
        }else {
            return city;
        }
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
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
}
