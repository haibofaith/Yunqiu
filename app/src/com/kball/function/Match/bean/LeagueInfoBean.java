package com.kball.function.Match.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/7.
 */

public class LeagueInfoBean implements Serializable {
    private String apply_end_time;//报名开始时间,yyyy-MM-dd HH：mm：ss
    private String apply_start_time;//报名结束时间,yyyy-MM-dd HH：mm：ss
    private String background;//联赛背景图
    private String city;//市
    private String end_time;//比赛结束时间,yyyy-MM-dd HH：mm
    private String game_system;//赛制
    private String if_notice;
    private String if_open;
    private String league_abbreviation;//联赛名称（简称）
    private String league_icon;//	联赛图标
    private String league_id;//	主键
    private String league_name;//联赛全称
    private String league_site;//场地，多个场地以”,”隔开
    private String league_type;//联赛类型
    private String province;//省
    private String start_time;//比赛开始时间,yyyy-MM-dd HH：mm
    private String status;//1：报名中 2：报名截至 3：进行中 4：结束
    private String direct;//主办方，多个以”,”号隔开
    private String undertake;//承办方，多个以”,”号隔开
    private String assisting;//协办方，多个以”,”号隔开
    private String area;//区/县
    private String sponsor;//赞助商，多个以”,”号隔开
    private String introduce;//	联赛介绍

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAssisting() {
        return assisting;
    }

    public void setAssisting(String assisting) {
        this.assisting = assisting;
    }

    public String getUndertake() {
        return undertake;
    }

    public void setUndertake(String undertake) {
        this.undertake = undertake;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getApply_end_time() {
        return apply_end_time;
    }

    public void setApply_end_time(String apply_end_time) {
        this.apply_end_time = apply_end_time;
    }

    public String getApply_start_time() {
        return apply_start_time;
    }

    public void setApply_start_time(String apply_start_time) {
        this.apply_start_time = apply_start_time;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
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

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getGame_system() {
        return game_system;
    }

    public void setGame_system(String game_system) {
        this.game_system = game_system;
    }

    public String getIf_notice() {
        return if_notice;
    }

    public void setIf_notice(String if_notice) {
        this.if_notice = if_notice;
    }

    public String getIf_open() {
        return if_open;
    }

    public void setIf_open(String if_open) {
        this.if_open = if_open;
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

    public String getLeague_id() {
        return league_id;
    }

    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }

    public String getLeague_site() {
        return league_site;
    }

    public void setLeague_site(String league_site) {
        this.league_site = league_site;
    }

    public String getLeague_type() {
        return league_type;
    }

    public void setLeague_type(String league_type) {
        this.league_type = league_type;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
