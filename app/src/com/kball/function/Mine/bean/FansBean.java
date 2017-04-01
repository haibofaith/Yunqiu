package com.kball.function.Mine.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/2/17.
 */

public class FansBean implements Serializable{
    /**
     * "user_id":"xxxxx",
     "nickname":"xxxxx",
     "portrait":"xxxxx",
     "position":"xxxxx,xxxxx",
     "province":"xxxxx",
     "city":"xxxxx",
     "area":"xxxx"
     */

    private String user_id;
    private String nickname;
    private String portrait;
    private String position;
    private String province;
    private String city;
    private String area;

    /**
     * "team_id":"xxxxx",
     "team_name":"xxxxx",
     "badge":"xxxxx",
     "province":"xxxxx",
     "city":"xxxxx",
     "area":"xxxx",
     "number":11
     * @return
     */


    private String team_id;
    private String team_name;
    private String badge;
    private String number;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        if (("市辖区").equals(city)){
            return "";
        }else {
            return city;
        }
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
