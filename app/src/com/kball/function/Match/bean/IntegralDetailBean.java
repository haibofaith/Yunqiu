package com.kball.function.Match.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/8.
 */

public class IntegralDetailBean implements Serializable {
    private String game_id;
    private String game_time;
    private String score_teamA;
    private String score_teamB;
    private String teamA_badge;
    private String teamA_id;
    private String teamA_name;
    private String teamB_badge;
    private String teamB_id;
    private String teamB_name;

    /**
     *  "ranking_id":"xxxx",
     "schedule_id":"xxxx",
     "team_id":"xxxx",
     "current_ranking":1,
     "lifting":1,
     "game_number":1,
     "victory":1,
     "flat":1,
     "negation":1,
     "goal":1,
     "lose",1,
     "integral":1
     * @return
     */
    private String ranking_id;
    private String schedule_id;
    private String team_id;
    private String team_name;
    private String current_ranking;
    private String lifting;
    private String game_number;
    private String victory;
    private String flat;
    private String negation;
    private String goal;
    private String lose;
    private String integral;
    private String badge;

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getRanking_id() {
        return ranking_id;
    }

    public void setRanking_id(String ranking_id) {
        this.ranking_id = ranking_id;
    }

    public String getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(String schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getCurrent_ranking() {
        return current_ranking;
    }

    public void setCurrent_ranking(String current_ranking) {
        this.current_ranking = current_ranking;
    }

    public String getLifting() {
        return lifting;
    }

    public void setLifting(String lifting) {
        this.lifting = lifting;
    }

    public String getGame_number() {
        return game_number;
    }

    public void setGame_number(String game_number) {
        this.game_number = game_number;
    }

    public String getVictory() {
        return victory;
    }

    public void setVictory(String victory) {
        this.victory = victory;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getNegation() {
        return negation;
    }

    public void setNegation(String negation) {
        this.negation = negation;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getLose() {
        return lose;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
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

    public String getTeamA_id() {
        return teamA_id;
    }

    public void setTeamA_id(String teamA_id) {
        this.teamA_id = teamA_id;
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

    public String getTeamB_id() {
        return teamB_id;
    }

    public void setTeamB_id(String teamB_id) {
        this.teamB_id = teamB_id;
    }

    public String getTeamB_name() {
        return teamB_name;
    }

    public void setTeamB_name(String teamB_name) {
        this.teamB_name = teamB_name;
    }
}
