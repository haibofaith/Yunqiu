package com.kball.function.Match.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 2017/3/7.
 */

public class MatchAboutDataBean<T> implements Serializable {
    private ArrayList<T> entire;
    private ArrayList<T> goal;
    private ArrayList<T> own;
    private ArrayList<T> penalty;
    private ArrayList<T> red;
    private ArrayList<T> yellow;
    private ArrayList<NewEntireBean> entire_subsumption;

    public ArrayList<NewEntireBean> getEntire_subsumption() {
        return entire_subsumption;
    }

    public void setEntire_subsumption(ArrayList<NewEntireBean> entire_subsumption) {
        this.entire_subsumption = entire_subsumption;
    }

    public ArrayList<T> getEntire() {
        return entire;
    }

    public void setEntire(ArrayList<T> entire) {
        this.entire = entire;
    }

    public ArrayList<T> getGoal() {
        return goal;
    }

    public void setGoal(ArrayList<T> goal) {
        this.goal = goal;
    }

    public ArrayList<T> getOwn() {
        return own;
    }

    public void setOwn(ArrayList<T> own) {
        this.own = own;
    }

    public ArrayList<T> getPenalty() {
        return penalty;
    }

    public void setPenalty(ArrayList<T> penalty) {
        this.penalty = penalty;
    }

    public ArrayList<T> getRed() {
        return red;
    }

    public void setRed(ArrayList<T> red) {
        this.red = red;
    }

    public ArrayList<T> getYellow() {
        return yellow;
    }

    public void setYellow(ArrayList<T> yellow) {
        this.yellow = yellow;
    }
}
