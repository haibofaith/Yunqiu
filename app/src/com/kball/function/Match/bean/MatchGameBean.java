package com.kball.function.Match.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/5.
 */

public class MatchGameBean implements Serializable {
    /**
     * "event_id":"xxxxx",
     * "game_type":1,
     * "sponsor_id":"xxxxx",
     * "sponsor_name":"xxxxxx",
     * "sponsor_badge":"xxxxxx",
     * "rival_id":"xxxxxx",
     * "rival_name":"xxxxxx",
     * "rival_badge":"xxxxxx",
     * "game_time":yyyy-MM-dd HH:mm:ss,
     * "site":"xxxx",
     * "event_status":1,
     * "button"1
     */
    private String event_id;
    private String game_type;
    private String sponsor_id;
    private String sponsor_name;
    private String sponsor_badge;
    private String rival_id;
    private String rival_name;
    private String rival_badge;
    private String site;
    private String event_status;
    private String button;

    private String apply_end_time;
    private String classify;
    private String continue_time;
    private String entering_status;
    private String entry_teamA;
    private String entry_teamB;
    private String game_cost;
    private String game_id;
    private String game_name;
    private String game_site;
    private String game_status;
    private String game_system;
    private String game_time;
    private String isVideo;
    private String score_status;
    private String score_teamA;
    private String score_teamB;
    private String spot_teamA;
    private String spot_teamB;
    private String teamA_badge;
    private String teamA_name;
    private String teamB_badge;
    private String teamB_name;
    private String uniform_teamA;
    private String uniform_teamB;
    private String user_id;
    private String value_added;

    public String getApply_end_time() {
        return apply_end_time;
    }

    public void setApply_end_time(String apply_end_time) {
        this.apply_end_time = apply_end_time;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getContinue_time() {
        return continue_time;
    }

    public void setContinue_time(String continue_time) {
        this.continue_time = continue_time;
    }

    public String getEntering_status() {
        return entering_status;
    }

    public void setEntering_status(String entering_status) {
        this.entering_status = entering_status;
    }

    public String getEntry_teamA() {
        return entry_teamA;
    }

    public void setEntry_teamA(String entry_teamA) {
        this.entry_teamA = entry_teamA;
    }

    public String getEntry_teamB() {
        return entry_teamB;
    }

    public void setEntry_teamB(String entry_teamB) {
        this.entry_teamB = entry_teamB;
    }

    public String getGame_cost() {
        return game_cost;
    }

    public void setGame_cost(String game_cost) {
        this.game_cost = game_cost;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_site() {
        return game_site;
    }

    public void setGame_site(String game_site) {
        this.game_site = game_site;
    }

    public String getGame_status() {
        return game_status;
    }

    public void setGame_status(String game_status) {
        this.game_status = game_status;
    }

    public String getGame_system() {
        return game_system;
    }

    public void setGame_system(String game_system) {
        this.game_system = game_system;
    }

    public String getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(String isVideo) {
        this.isVideo = isVideo;
    }

    public String getScore_status() {
        return score_status;
    }

    public void setScore_status(String score_status) {
        this.score_status = score_status;
    }

    public String getScore_teamA() {
        return score_teamA;
    }

    public void setScore_teamA(String score_teamA) {
        this.score_teamA = score_teamA;
    }

    public String getScore_teamB() {
        return score_teamB;
    }

    public void setScore_teamB(String score_teamB) {
        this.score_teamB = score_teamB;
    }

    public String getSpot_teamA() {
        return spot_teamA;
    }

    public void setSpot_teamA(String spot_teamA) {
        this.spot_teamA = spot_teamA;
    }

    public String getSpot_teamB() {
        return spot_teamB;
    }

    public void setSpot_teamB(String spot_teamB) {
        this.spot_teamB = spot_teamB;
    }

    public String getTeamA_badge() {
        return teamA_badge;
    }

    public void setTeamA_badge(String teamA_badge) {
        this.teamA_badge = teamA_badge;
    }

    public String getTeamA_name() {
        return teamA_name;
    }

    public void setTeamA_name(String teamA_name) {
        this.teamA_name = teamA_name;
    }

    public String getTeamB_badge() {
        return teamB_badge;
    }

    public void setTeamB_badge(String teamB_badge) {
        this.teamB_badge = teamB_badge;
    }

    public String getTeamB_name() {
        return teamB_name;
    }

    public void setTeamB_name(String teamB_name) {
        this.teamB_name = teamB_name;
    }

    public String getUniform_teamA() {
        return uniform_teamA;
    }

    public void setUniform_teamA(String uniform_teamA) {
        this.uniform_teamA = uniform_teamA;
    }

    public String getUniform_teamB() {
        return uniform_teamB;
    }

    public void setUniform_teamB(String uniform_teamB) {
        this.uniform_teamB = uniform_teamB;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getValue_added() {
        return value_added;
    }

    public void setValue_added(String value_added) {
        this.value_added = value_added;
    }


    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public String getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id(String sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    public String getSponsor_name() {
        return sponsor_name;
    }

    public void setSponsor_name(String sponsor_name) {
        this.sponsor_name = sponsor_name;
    }

    public String getSponsor_badge() {
        return sponsor_badge;
    }

    public void setSponsor_badge(String sponsor_badge) {
        this.sponsor_badge = sponsor_badge;
    }

    public String getRival_id() {
        return rival_id;
    }

    public void setRival_id(String rival_id) {
        this.rival_id = rival_id;
    }

    public String getRival_name() {
        return rival_name;
    }

    public void setRival_name(String rival_name) {
        this.rival_name = rival_name;
    }

    public String getRival_badge() {
        return rival_badge;
    }

    public void setRival_badge(String rival_badge) {
        this.rival_badge = rival_badge;
    }

    public String getGame_time() {
        return game_time;
    }

    public void setGame_time(String game_time) {
        this.game_time = game_time;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEvent_status() {
        return event_status;
    }

    public void setEvent_status(String event_status) {
        this.event_status = event_status;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}
