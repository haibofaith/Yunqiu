package com.kball.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/11.
 */

public class VersionBean implements Serializable {
    /**
     *         "isUpdate":true,
     "versionInfo":{
     "outside_version_number":"1.0.0",
     "house_version_number":1,
     "description":"xxxxx",
     "download_url":"xxxxx",
     "forceUpload":1
     }
     */

    private boolean isUpdate;
    private VersionInfoBean versionInfo;

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    public VersionInfoBean getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(VersionInfoBean versionInfo) {
        this.versionInfo = versionInfo;
    }
}
