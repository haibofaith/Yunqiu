package com.kball.function.Match.bean;

import java.io.Serializable;

/**
 * league_id	String	赛事id
 * league_abbreviation	String	赛事名称
 * league_icon	String	赛事图标
 * game_system	int	赛制
 * start_time	Date	赛事开始时间
 * end_time	Date	赛事结束时间
 * apply_start_time	Date	赛事报名开始时间
 * apply_end_time	Date	赛事报名结束时间
 * league_site	String	赛事比赛场地，多个以”,”隔开
 * province	String	省
 * city	String	市
 * area	String	区/县
 * status	int	赛事状态。 1：报名中 2：报名截至 3：进行中 4：结束
 */

public class MatchTabTwoBean implements Serializable {
    private String league_id;
    private String league_abbreviation;
    private String league_icon;
    private String game_system;
    private String start_time;
    private String end_time;
    private String apply_start_time;
    private String apply_end_time;
    private String league_site;
    private String province;
    private String city;
    private String area;
    private String status;

    public String getLeague_id() {
        return league_id;
    }

    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }

    public String getLeague_abbreviation() {
        return league_abbreviation;
    }

    public void setLeague_abbreviation(String league_abbreviation) {
        this.league_abbreviation = league_abbreviation;
    }

    public String getLeague_icon() {
        return league_icon;
    }

    public void setLeague_icon(String league_icon) {
        this.league_icon = league_icon;
    }

    public String getGame_system() {
        return game_system;
    }

    public void setGame_system(String game_system) {
        this.game_system = game_system;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getApply_start_time() {
        return apply_start_time;
    }

    public void setApply_start_time(String apply_start_time) {
        this.apply_start_time = apply_start_time;
    }

    public String getApply_end_time() {
        return apply_end_time;
    }

    public void setApply_end_time(String apply_end_time) {
        this.apply_end_time = apply_end_time;
    }

    public String getLeague_site() {
        return league_site;
    }

    public void setLeague_site(String league_site) {
        this.league_site = league_site;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
