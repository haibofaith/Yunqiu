package com.kball.function.CloudBall.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/26.
 */

public class MessageRanksBean implements Serializable {
    private String is_look;
    private String message;
    private String message_id;
    private String message_title;
    private String message_type;
    private String push_time;
    private String specific_id;
    private String specific_type;
    private String type_id;
    private String user_id;

    public String getIs_look() {
        return is_look;
    }

    public void setIs_look(String is_look) {
        this.is_look = is_look;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getMessage_title() {
        return message_title;
    }

    public void setMessage_title(String message_title) {
        this.message_title = message_title;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getPush_time() {
        return push_time;
    }

    public void setPush_time(String push_time) {
        this.push_time = push_time;
    }

    public String getSpecific_id() {
        return specific_id;
    }

    public void setSpecific_id(String specific_id) {
        this.specific_id = specific_id;
    }

    public String getSpecific_type() {
        return specific_type;
    }

    public void setSpecific_type(String specific_type) {
        this.specific_type = specific_type;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
