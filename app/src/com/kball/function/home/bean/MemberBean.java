package com.kball.function.home.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/7.
 */

public class MemberBean implements Serializable {
    private String member_number;
    private ArrayList<MemberListBean> member_list;

    public String getMember_number() {
        return member_number;
    }

    public void setMember_number(String member_number) {
        this.member_number = member_number;
    }

    public ArrayList<MemberListBean> getMember_list() {
        return member_list;
    }

    public void setMember_list(ArrayList<MemberListBean> member_list) {
        this.member_list = member_list;
    }
}
