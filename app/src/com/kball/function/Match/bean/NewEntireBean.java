package com.kball.function.Match.bean;

import java.io.Serializable;

/**
 * Created by xiaole.wang on 17/3/11.
 */

public class NewEntireBean  implements Serializable{
    private String total_name;
    private String total_numberA;
    private String total_numberB;

    public String getTotal_name() {
        return total_name;
    }

    public void setTotal_name(String total_name) {
        this.total_name = total_name;
    }

    public String getTotal_numberA() {
        return total_numberA;
    }

    public void setTotal_numberA(String total_numberA) {
        this.total_numberA = total_numberA;
    }

    public String getTotal_numberB() {
        return total_numberB;
    }

    public void setTotal_numberB(String total_numberB) {
        this.total_numberB = total_numberB;
    }
}
