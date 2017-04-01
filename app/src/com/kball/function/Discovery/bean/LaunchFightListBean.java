package com.kball.function.Discovery.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/2/22.
 * team_id	String	球队id
 * team_name	String	球队名称
 * badge	String	队徽
 * game_id	String	比赛id
 * game_time	long	比赛时间
 * game_site	String	比赛场地
 * game_system	int	赛制
 */

public class LaunchFightListBean implements Serializable {
    private String team_id;
    private String team_name;
    private String badge;
    private String game_id;
    private String game_time;
    private String game_site;
    private String game_system;
    private String area;
    private String city;
    private String province;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getGame_time() {
        return game_time;
    }

    public void setGame_time(String game_time) {
        this.game_time = game_time;
    }

    public String getGame_site() {
        return game_site;
    }

    public void setGame_site(String game_site) {
        this.game_site = game_site;
    }

    public String getGame_system() {
        return game_system;
    }

    public void setGame_system(String game_system) {
        this.game_system = game_system;
    }
}
