package com.kball.function.CloudBall.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/14.
 */

public class CityBean implements Serializable {
    /**
     *             id:1,
     "province_id","xxxxx",
     "city":"xxxx"
     "city_id":"xxx"
     */
    private String id;
    private String province_id;
    private String city;
    private String city_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
