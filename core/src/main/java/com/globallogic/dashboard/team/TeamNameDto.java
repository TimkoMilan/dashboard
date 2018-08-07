package com.globallogic.dashboard.team;

import java.io.Serializable;

public class TeamNameDto implements Serializable {
    private String teamName;
    private String focus;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }
}
