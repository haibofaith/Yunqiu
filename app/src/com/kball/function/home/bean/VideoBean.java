package com.kball.function.home.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/21.
 */

public class VideoBean implements Serializable {
    private String video_id;
    private String video_title;
    private String video_url;
    private String image_url;
    private String watch_number;

    public String getWatch_number() {
        return watch_number;
    }

    public void setWatch_number(String watch_number) {
        this.watch_number = watch_number;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
