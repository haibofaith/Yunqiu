package com.kball.function.CloudBall.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/9.
 */

public class StartGameBean implements Serializable{

    private String badge;
    private String classify;
    private String event_match;
    private String game_id;
    private String game_name;
    private String game_site;
    private String game_time;
    private String game_type;
    private ArrayList<MatchBean> match;
    private String name;
    private String registration_status;
    private String team_match;
    private String uniform_team;


    public String getUniform_team() {
        return uniform_team;
    }

    public void setUniform_team(String uniform_team) {
        this.uniform_team = uniform_team;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getEvent_match() {
        return event_match;
    }

    public void setEvent_match(String event_match) {
        this.event_match = event_match;
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

    public String getGame_time() {
        return game_time;
    }

    public void setGame_time(String game_time) {
        this.game_time = game_time;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public ArrayList<MatchBean> getMatch() {
        return match;
    }

    public void setMatch(ArrayList<MatchBean> match) {
        this.match = match;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistration_status() {
        return registration_status;
    }

    public void setRegistration_status(String registration_status) {
        this.registration_status = registration_status;
    }

    public String getTeam_match() {
        return team_match;
    }

    public void setTeam_match(String team_match) {
        this.team_match = team_match;
    }
}
