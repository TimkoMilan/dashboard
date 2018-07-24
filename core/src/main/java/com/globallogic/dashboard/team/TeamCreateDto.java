package com.globallogic.dashboard.team;

import java.io.Serializable;

public class TeamCreateDto implements Serializable {


    private String name;
    private String focus;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

}
