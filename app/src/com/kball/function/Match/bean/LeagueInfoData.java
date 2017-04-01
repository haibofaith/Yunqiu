package com.kball.function.Match.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/7.
 */

public class LeagueInfoData<T> implements Serializable{
    private String showButton;//是否显示报名按钮 1：不显示 2：显示
    private String sign_status;//	报名状态 1：待审核 2：通过 3：拒绝 4:未报名
    private String share_url;
    private T info;//赛事信息

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public String getShowButton() {
        return showButton;
    }

    public void setShowButton(String showButton) {
        this.showButton = showButton;
    }

    public String getSign_status() {
        return sign_status;
    }

    public void setSign_status(String sign_status) {
        this.sign_status = sign_status;
    }
}

