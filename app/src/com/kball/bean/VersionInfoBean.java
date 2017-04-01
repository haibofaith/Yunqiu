package com.kball.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/11.
 */

public class VersionInfoBean implements Serializable {

    /**
     * "outside_version_number":"1.0.0",
     "house_version_number":1,
     "description":"xxxxx",
     "download_url":"xxxxx",
     "forceUpload":1
     */
    private String outside_version_number;
    private String house_version_number;
    private String description;
    private String download_url;
    private String forceUpload;


    public String getOutside_version_number() {
        return outside_version_number;
    }

    public void setOutside_version_number(String outside_version_number) {
        this.outside_version_number = outside_version_number;
    }

    public String getHouse_version_number() {
        return house_version_number;
    }

    public void setHouse_version_number(String house_version_number) {
        this.house_version_number = house_version_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getForceUpload() {
        return forceUpload;
    }

    public void setForceUpload(String forceUpload) {
        this.forceUpload = forceUpload;
    }
}
