package com.kball.function.Match.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/6.
 */

public class VideoBean implements Serializable {
    private String brief;
    private String classify;
    private String createTime;
    private String if_show;
    private String league_id;
    private String screenshots;
    private String user_id;
    private String vid;
    private String video_address_high;
    private String video_address_ordinary;
    private String video_address_smooth;
    private String video_id;
    private String video_name;
    private String video_tag;
    private String video_type;
    private String browse_number  = "0";

    public String getBrowse_number() {
        return browse_number;
    }

    public void setBrowse_number(String browse_number) {
        this.browse_number = browse_number;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIf_show() {
        return if_show;
    }

    public void setIf_show(String if_show) {
        this.if_show = if_show;
    }

    public String getLeague_id() {
        return league_id;
    }

    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }

    public String getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(String screenshots) {
        this.screenshots = screenshots;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getVideo_address_high() {
        return video_address_high;
    }

    public void setVideo_address_high(String video_address_high) {
        this.video_address_high = video_address_high;
    }

    public String getVideo_address_ordinary() {
        return video_address_ordinary;
    }

    public void setVideo_address_ordinary(String video_address_ordinary) {
        this.video_address_ordinary = video_address_ordinary;
    }

    public String getVideo_address_smooth() {
        return video_address_smooth;
    }

    public void setVideo_address_smooth(String video_address_smooth) {
        this.video_address_smooth = video_address_smooth;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public String getVideo_tag() {
        return video_tag;
    }

    public void setVideo_tag(String video_tag) {
        this.video_tag = video_tag;
    }

    public String getVideo_type() {
        return video_type;
    }

    public void setVideo_type(String video_type) {
        this.video_type = video_type;
    }
}
