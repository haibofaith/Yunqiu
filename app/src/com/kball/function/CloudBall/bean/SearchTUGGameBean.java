package com.kball.function.CloudBall.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/18.
 */

public class SearchTUGGameBean implements Serializable {
    private String area;
    private String start_time;
    private String game_system;
    private String league_icon;
    private String province;
    private String city;
    private String league_name;
    private String end_time;
    private String league_site;
    private String league_id;
    private String status;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getGame_system() {
        return game_system;
    }

    public void setGame_system(String game_system) {
        this.game_system = game_system;
    }

    public String getLeague_icon() {
        return league_icon;
    }

    public void setLeague_icon(String league_icon) {
        this.league_icon = league_icon;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getLeague_site() {
        return league_site;
    }

    public void setLeague_site(String league_site) {
        this.league_site = league_site;
    }

    public String getLeague_id() {
        return league_id;
    }

    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
