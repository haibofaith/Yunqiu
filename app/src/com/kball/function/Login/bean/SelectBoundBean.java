package com.kball.function.Login.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/16.
 */

public class SelectBoundBean implements Serializable {
    private String identifier;
    private String identity_type;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentity_type() {
        return identity_type;
    }

    public void setIdentity_type(String identity_type) {
        this.identity_type = identity_type;
    }
}
