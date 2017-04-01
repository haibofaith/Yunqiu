package com.kball.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/1.
 */

public class BaseToBean<T> implements Serializable {
    private String type;
    private String total;
    private String pageSize;
    private String pageNum;
    private ArrayList<T> info;
    private ArrayList<T> fans;
    private ArrayList<T> focus;

    public ArrayList<T> getFocus() {
        return focus;
    }

    public void setFocus(ArrayList<T> focus) {
        this.focus = focus;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public ArrayList<T> getFans() {
        return fans;
    }

    public void setFans(ArrayList<T> fans) {
        this.fans = fans;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<T> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<T> info) {
        this.info = info;
    }
}
