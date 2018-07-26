package com.globallogic.dashboard.member;

import java.io.Serializable;

public class MemberDto implements Serializable {
    private String name;
    private String SearchString;
    private Long teamId;


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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
