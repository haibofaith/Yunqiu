package com.kball.function.home.bean;

import com.kball.function.Mine.bean.LeagueBean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/4.
 */

public class PersonalBean implements Serializable {

    private String assists;
    private String flat;
    private String game_number;
    private String goal;
    private String league_number;
    private String lose;
    private String no_entry;
    private String red_card;
    private String victory;
    private String yellow_card;
    private LeagueBean league;

    public LeagueBean getLeague() {
        return league;
    }

    public void setLeague(LeagueBean league) {
        this.league = league;
    }

    public String getAssists() {
        return assists;
    }

    public void setAssists(String assists) {
        this.assists = assists;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getGame_number() {
        return game_number;
    }

    public void setGame_number(String game_number) {
        this.game_number = game_number;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getLeague_number() {
        return league_number;
    }

    public void setLeague_number(String league_number) {
        this.league_number = league_number;
    }

    public String getLose() {
        return lose;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

    public String getNo_entry() {
        return no_entry;
    }

    public void setNo_entry(String no_entry) {
        this.no_entry = no_entry;
    }

    public String getRed_card() {
        return red_card;
    }

    public void setRed_card(String red_card) {
        this.red_card = red_card;
    }

    public String getVictory() {
        return victory;
    }

    public void setVictory(String victory) {
        this.victory = victory;
    }

    public String getYellow_card() {
        return yellow_card;
    }

    public void setYellow_card(String yellow_card) {
        this.yellow_card = yellow_card;
    }
}
