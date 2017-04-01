package com.kball.function.Discovery.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 2017/3/5.
 */

public class DisBaseListBean implements Serializable {
    private String pageTotal;
    private String pageSize;
    private String pageNum;
    private ArrayList<LaunchFightListBean> list;
    private AboutBean about;

    public AboutBean getAbout() {
        return about;
    }

    public void setAbout(AboutBean about) {
        this.about = about;
    }

    public ArrayList<LaunchFightListBean> getList() {
        return list;
    }

    public void setList(ArrayList<LaunchFightListBean> list) {
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
