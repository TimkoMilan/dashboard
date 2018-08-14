package com.globallogic.dashboard.team;

import java.io.Serializable;

public class TeamNameDto implements Serializable {
    private Long id;
    private String teamName;
    private String focus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
