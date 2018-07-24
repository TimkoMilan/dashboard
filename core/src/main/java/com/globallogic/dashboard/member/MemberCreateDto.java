package com.globallogic.dashboard.member;

import java.io.Serializable;

public class MemberCreateDto implements Serializable {


    private String name;

    private String position;

    private String billingValue;

    private String focus;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBillingValue() {
        return billingValue;
    }

    public void setBillingValue(String billingValue) {
        this.billingValue = billingValue;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

}
