package com.kball.function.home.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/4.
 */

public class ListBaseBean<T> implements Serializable{
    private ArrayList<T> data;
    private String  error_code;
    private String  msg;

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
