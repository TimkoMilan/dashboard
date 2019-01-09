package com.globallogic.dashboard.user;

import java.io.Serializable;

public class UserActivationDto implements Serializable {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
