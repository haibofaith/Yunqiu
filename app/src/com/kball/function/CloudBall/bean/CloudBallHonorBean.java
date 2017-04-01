package com.kball.function.CloudBall.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/2/18.
 */

public class CloudBallHonorBean implements Serializable {
    /**
     *  "league_id":"xxxx",
     "img_url":"xxxx",
     "honor_name":"xxxx",
     "team_id":"xxxx"
     */
    private String league_id;
    private String img_url;
    private String honor_name;
    private String team_id;

    public String getLeague_id() {
        return league_id;
    }

    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getHonor_name() {
        return honor_name;
    }

    public void setHonor_name(String honor_name) {
        this.honor_name = honor_name;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }
}
