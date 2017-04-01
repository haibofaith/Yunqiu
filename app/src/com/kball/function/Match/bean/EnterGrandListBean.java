package com.kball.function.Match.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 2017/3/25.
 */

public class EnterGrandListBean implements Serializable {
    private String game_id;
    private String score_teamA;
    private String score_teamB;
    private ArrayList<GoalBean> goal;
    private ArrayList<PenaltyBean> penalty;
    private ArrayList<RedCardBean> red_card;
    private ArrayList<RedCardBean> yellow_card;
    private ArrayList<RedCardBean> own_goal;

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
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

    public ArrayList<GoalBean> getGoal() {
        return goal;
    }

    public void setGoal(ArrayList<GoalBean> goal) {
        this.goal = goal;
    }

    public ArrayList<PenaltyBean> getPenalty() {
        return penalty;
    }

    public void setPenalty(ArrayList<PenaltyBean> penalty) {
        this.penalty = penalty;
    }

    public ArrayList<RedCardBean> getRed_card() {
        return red_card;
    }

    public void setRed_card(ArrayList<RedCardBean> red_card) {
        this.red_card = red_card;
    }

    public ArrayList<RedCardBean> getYellow_card() {
        return yellow_card;
    }

    public void setYellow_card(ArrayList<RedCardBean> yellow_card) {
        this.yellow_card = yellow_card;
    }

    public ArrayList<RedCardBean> getOwn_goal() {
        return own_goal;
    }

    public void setOwn_goal(ArrayList<RedCardBean> own_goal) {
        this.own_goal = own_goal;
    }
}
