package com.globallogic.dashboard.team;

import com.globallogic.dashboard.member.Member;
import com.globallogic.dashboard.sprint.SprintData;

import java.io.Serializable;
import java.util.Set;

public class TeamDto implements Serializable {

    private String teamName;
    private String focus;
    private Set<Member> members;
    private Set<SprintData> sprint;


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

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public Set<SprintData> getSprint() {
        return sprint;
    }

    public void setSprint(Set<SprintData> sprint) {
        this.sprint = sprint;
    }
}
