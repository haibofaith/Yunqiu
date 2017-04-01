package com.kball.function.Login.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/2/28.
 */

public class UserRegisterBean implements Serializable {
    private String user_id;
    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String bound_status;
    private String openid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getBound_status() {
        return bound_status;
    }

    public void setBound_status(String bound_status) {
        this.bound_status = bound_status;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
