package com.kball.function.home.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/7.
 */

public class SubjoinInfoBean implements Serializable {
    private String focus_status;
    private String identity;
    private String jurisdiction;
    private String mean_age;
    private String invitationStatus;
    private String invitation_id;

    public String getInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(String invitationStatus) {
        this.invitationStatus = invitationStatus;
    }

    public String getInvitation_id() {
        return invitation_id;
    }

    public void setInvitation_id(String invitation_id) {
        this.invitation_id = invitation_id;
    }

    public String getFocus_status() {
        return focus_status;
    }

    public void setFocus_status(String focus_status) {
        this.focus_status = focus_status;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public String getMean_age() {
        return mean_age;
    }

    public void setMean_age(String mean_age) {
        this.mean_age = mean_age;
    }
}
