package com.kball.function.CloudBall.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/16.
 */

public class SearchTUGList implements Serializable {
    private ArrayList<SearchTUGTeamBean> team;
    private ArrayList<InvitePlayerBean> user;
    private ArrayList<SearchTUGGameBean> game;
    private String team_pageNum;
    private String user_pageNum;
    private String game_pageNum;
    private String team_pageSize;
    private String user_pageSize;
    private String game_pageSize;
    private String team_pageTotal;
    private String user_pageTotal;
    private String game_pageTotal;

    public ArrayList<SearchTUGTeamBean> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<SearchTUGTeamBean> team) {
        this.team = team;
    }

    public ArrayList<InvitePlayerBean> getUser() {
        return user;
    }

    public void setUser(ArrayList<InvitePlayerBean> user) {
        this.user = user;
    }

    public ArrayList<SearchTUGGameBean> getGame() {
        return game;
    }

    public void setGame(ArrayList<SearchTUGGameBean> game) {
        this.game = game;
    }

    public String getGame_pageNum() {
        return game_pageNum;
    }

    public void setGame_pageNum(String game_pageNum) {
        this.game_pageNum = game_pageNum;
    }

    public String getGame_pageSize() {
        return game_pageSize;
    }

    public void setGame_pageSize(String game_pageSize) {
        this.game_pageSize = game_pageSize;
    }

    public String getGame_pageTotal() {
        return game_pageTotal;
    }

    public void setGame_pageTotal(String game_pageTotal) {
        this.game_pageTotal = game_pageTotal;
    }


    public String getUser_pageNum() {
        return user_pageNum;
    }

    public void setUser_pageNum(String user_pageNum) {
        this.user_pageNum = user_pageNum;
    }

    public String getUser_pageSize() {
        return user_pageSize;
    }

    public void setUser_pageSize(String user_pageSize) {
        this.user_pageSize = user_pageSize;
    }

    public String getUser_pageTotal() {
        return user_pageTotal;
    }

    public void setUser_pageTotal(String user_pageTotal) {
        this.user_pageTotal = user_pageTotal;
    }


    public String getTeam_pageNum() {
        return team_pageNum;
    }

    public void setTeam_pageNum(String team_pageNum) {
        this.team_pageNum = team_pageNum;
    }

    public String getTeam_pageSize() {
        return team_pageSize;
    }

    public void setTeam_pageSize(String team_pageSize) {
        this.team_pageSize = team_pageSize;
    }

    public String getTeam_pageTotal() {
        return team_pageTotal;
    }

    public void setTeam_pageTotal(String team_pageTotal) {
        this.team_pageTotal = team_pageTotal;
    }
}
