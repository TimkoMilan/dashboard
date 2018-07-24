package com.globallogic.dashboard.member;

import com.globallogic.dashboard.team.Team;

import java.io.Serializable;

public class MemberDto implements Serializable {
    private String name;
    private String SearchString;
    private Team team;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchString() {
        return SearchString;
    }

    public void setSearchString(String searchString) {
        SearchString = searchString;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


}
