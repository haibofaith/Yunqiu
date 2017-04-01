package com.kball.function.CloudBall.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/9.
 */

public class EndGameBean implements Serializable {
    private String classify;
    private String game_id;
    private String game_name;
    private String game_time;
    private String score_teamA;
    private String score_teamB;
    private String teamA_badge;
    private String teamA_name;
    private String teamB_badge;
    private String teamB_name;
    private String isVideo;

    public String getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(String isVideo) {
        this.isVideo = isVideo;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
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

    public String getGame_time() {
        return game_time;
    }

    public void setGame_time(String game_time) {
        this.game_time = game_time;
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
}
