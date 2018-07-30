package com.globallogic.dashboard.team;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TeamCreateDto implements Serializable {

    @NotNull
    private String name;
    @NotNull
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
