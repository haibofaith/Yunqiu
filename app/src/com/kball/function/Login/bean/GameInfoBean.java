package com.kball.function.Login.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/6.
 */

public class GameInfoBean<T> implements Serializable {
    private GameInfoOneBean game_info;
    private String identity;
    private String jurisdictionB;
    private String jurisdictionA;


    private ArrayList<T> member;
    private String join_status;
    private String isCollection;
    private String share_url;

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(String isCollection) {
        this.isCollection = isCollection;
    }

    public String getJurisdictionA() {
        return jurisdictionA;
    }

    public void setJurisdictionA(String jurisdictionA) {
        this.jurisdictionA = jurisdictionA;
    }

    public String getJoin_status() {
        return join_status;
    }

    public void setJoin_status(String join_status) {
        this.join_status = join_status;
    }

    public GameInfoOneBean getGame_info() {
        return game_info;
    }

    public void setGame_info(GameInfoOneBean game_info) {
        this.game_info = game_info;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getJurisdictionB() {
        return jurisdictionB;
    }

    public void setJurisdictionB(String jurisdictionB) {
        this.jurisdictionB = jurisdictionB;
    }

    public ArrayList<T> getMember() {
        return member;
    }

    public void setMember(ArrayList<T> member) {
        this.member = member;
    }
}
