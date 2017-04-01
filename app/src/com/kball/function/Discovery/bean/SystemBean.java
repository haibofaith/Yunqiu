package com.kball.function.Discovery.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/24.
 */

public class SystemBean implements Serializable{
    private String system_name;
    private String system_value;

    public String getSystem_name() {
        return system_name;
    }

    public void setSystem_name(String system_name) {
        this.system_name = system_name;
    }

    public String getSystem_value() {
        return system_value;
    }

    public void setSystem_value(String system_value) {
        this.system_value = system_value;
    }
}
