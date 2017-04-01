package com.kball.function.Login.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/2/28.
 */

public class BaseBean<T> implements Serializable {
    private String error_code;
    private String msg;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
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
