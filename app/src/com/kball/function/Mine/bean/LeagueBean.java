package com.kball.function.Mine.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/17.
 */

public class LeagueBean implements Serializable {
    /**
     * "area": "东城区",
     * "game_system": 5,
     * "city": "市辖区",
     * "apply_end_time": 1489226400000,
     * "end_time": 1492941600000,
     * "league_site": "天坛体育场",
     * "start_time": 1489305600000,
     * "league_icon": "/uploads/images/20170312/nWUnhIu3SQuGp8_L754dlA.jpeg",
     * "province": "北京市",
     * "apply_start_time": 1489305600000,
     * "league_abbreviation": "2017年口超联赛",
     * "league_id": "KSX6JnPLquArCP0JzgQr0u",
     * "status": 3
     */
    private String area;
    private String game_system;
    private String city;
    private String apply_end_time;
    private String end_time;
    private String league_site;
    private String start_time;
    private String league_icon;
    private String province;
    private String apply_start_time;
    private String league_abbreviation;
    private String league_id;
    private String status;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGame_system() {
        return game_system;
    }

    public void setGame_system(String game_system) {
        this.game_system = game_system;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getApply_end_time() {
        return apply_end_time;
    }

    public void setApply_end_time(String apply_end_time) {
        this.apply_end_time = apply_end_time;
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

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
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

    public String getApply_start_time() {
        return apply_start_time;
    }

    public void setApply_start_time(String apply_start_time) {
        this.apply_start_time = apply_start_time;
    }

    public String getLeague_abbreviation() {
        return league_abbreviation;
    }

    public void setLeague_abbreviation(String league_abbreviation) {
        this.league_abbreviation = league_abbreviation;
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
