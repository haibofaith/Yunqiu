package com.kball.function.CloudBall.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/9.
 */

public class RankBaseBean implements Serializable{

    private EndGameBean end_game;
    private ArrayList<RecommendTeam> recommend_team;
    private StartGameBean start_game;
    private ArrayList<DynamicBean> dynamic;

    public ArrayList<DynamicBean> getDynamic() {
        return dynamic;
    }

    public void setDynamic(ArrayList<DynamicBean> dynamic) {
        this.dynamic = dynamic;
    }

    public EndGameBean getEnd_game() {
        return end_game;
    }

    public void setEnd_game(EndGameBean end_game) {
        this.end_game = end_game;
    }

    public ArrayList<RecommendTeam> getRecommend_team() {
        return recommend_team;
    }

    public void setRecommend_team(ArrayList<RecommendTeam> recommend_team) {
        this.recommend_team = recommend_team;
    }

    public StartGameBean getStart_game() {
        return start_game;
    }

    public void setStart_game(StartGameBean start_game) {
        this.start_game = start_game;
    }
}
