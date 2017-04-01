package com.kball.function.CloudBall.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/28.
 */

public class DynamicBean implements Serializable {
    private String business_id;
    private String create_time;
    private String dynamic_desc;
    private String dynamic_id;
    private String dynamic_type;
    private String is_look;
    private String user_id;

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getDynamic_desc() {
        return dynamic_desc;
    }

    public void setDynamic_desc(String dynamic_desc) {
        this.dynamic_desc = dynamic_desc;
    }

    public String getDynamic_id() {
        return dynamic_id;
    }

    public void setDynamic_id(String dynamic_id) {
        this.dynamic_id = dynamic_id;
    }

    public String getDynamic_type() {
        return dynamic_type;
    }

    public void setDynamic_type(String dynamic_type) {
        this.dynamic_type = dynamic_type;
    }

    public String getIs_look() {
        return is_look;
    }

    public void setIs_look(String is_look) {
        this.is_look = is_look;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
