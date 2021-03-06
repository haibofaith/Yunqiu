package com.kball.function.Login.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 2017/3/16.
 */

public class BaseListDataBean<T> implements Serializable {
    private String error_code;
    private String msg;
    private ArrayList<T> data;

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

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }
}
