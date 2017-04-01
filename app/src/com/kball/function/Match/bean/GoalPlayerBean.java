package com.kball.function.Match.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/25.
 */

public class GoalPlayerBean implements Serializable {
    private String goalPlayer;
    private String helpPlayer;
    private String goalPlayerName;
    private String helpPlayerName;

    public String getGoalPlayerName() {
        return goalPlayerName;
    }

    public void setGoalPlayerName(String goalPlayerName) {
        this.goalPlayerName = goalPlayerName;
    }

    public String getHelpPlayerName() {
        return helpPlayerName;
    }

    public void setHelpPlayerName(String helpPlayerName) {
        this.helpPlayerName = helpPlayerName;
    }

    public String getGoalPlayer() {
        return goalPlayer;
    }

    public void setGoalPlayer(String goalPlayer) {
        this.goalPlayer = goalPlayer;
    }

    public String getHelpPlayer() {
        return helpPlayer;
    }

    public void setHelpPlayer(String helpPlayer) {
        this.helpPlayer = helpPlayer;
    }
}
