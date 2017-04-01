package com.kball.function.home.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xiaole.wang on 17/3/4.
 */

public class MyInfoBaseBean implements Serializable {
    private BasisBean basis;
    private CapacityBean capacity;
    private PersonalBean personal_data;
    private HonorBean honor;
    private String type;
    private ArrayList<ParticipateTeamBean> participate_team;

    public HonorBean getHonor() {
        return honor;
    }

    public void setHonor(HonorBean honor) {
        this.honor = honor;
    }

    public ArrayList<ParticipateTeamBean> getParticipate_team() {
        return participate_team;
    }

    public void setParticipate_team(ArrayList<ParticipateTeamBean> participate_team) {
        this.participate_team = participate_team;
    }

    public BasisBean getBasis() {
        return basis;
    }

    public void setBasis(BasisBean basis) {
        this.basis = basis;
    }

    public CapacityBean getCapacity() {
        return capacity;
    }

    public void setCapacity(CapacityBean capacity) {
        this.capacity = capacity;
    }

    public PersonalBean getPersonal_data() {
        return personal_data;
    }

    public void setPersonal_data(PersonalBean personal_data) {
        this.personal_data = personal_data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
