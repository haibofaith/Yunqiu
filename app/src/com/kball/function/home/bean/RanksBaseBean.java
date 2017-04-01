package com.kball.function.home.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/7.
 */

public class RanksBaseBean implements Serializable {
    private GradeBean grade;
    private HonorBean honor;
    private MemberBean member;
    private CapacityBean power;
    private SubjoinInfoBean subjoin_info;
    private TeamInfoBean team_info;

    public CapacityBean getPower() {
        return power;
    }

    public void setPower(CapacityBean power) {
        this.power = power;
    }

    public GradeBean getGrade() {
        return grade;
    }

    public void setGrade(GradeBean grade) {
        this.grade = grade;
    }

    public HonorBean getHonor() {
        return honor;
    }

    public void setHonor(HonorBean honor) {
        this.honor = honor;
    }

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public SubjoinInfoBean getSubjoin_info() {
        return subjoin_info;
    }

    public void setSubjoin_info(SubjoinInfoBean subjoin_info) {
        this.subjoin_info = subjoin_info;
    }

    public TeamInfoBean getTeam_info() {
        return team_info;
    }

    public void setTeam_info(TeamInfoBean team_info) {
        this.team_info = team_info;
    }
}
