package com.kball.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 2017/3/5.
 */

public class BaseListBean<T> implements Serializable {
    private String pageTotal;
    private String pageSize;
    private String pageNum;
    private int curve_rounds;
    private int total_rounds;
    private ArrayList<T> list;

    public int getCurve_rounds() {
        return curve_rounds;
    }

    public void setCurve_rounds(int curve_rounds) {
        this.curve_rounds = curve_rounds;
    }

    public int getTotal_rounds() {
        return total_rounds;
    }

    public void setTotal_rounds(int total_rounds) {
        this.total_rounds = total_rounds;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }


    public String getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(String pageTotal) {
        this.pageTotal = pageTotal;
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

}
