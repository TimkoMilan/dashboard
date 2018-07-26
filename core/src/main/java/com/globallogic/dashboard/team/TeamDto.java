package com.globallogic.dashboard.team;

import com.globallogic.dashboard.member.MemberDto;
import com.globallogic.dashboard.sprint.SprintDto;

import java.io.Serializable;
import java.util.Set;

public class TeamDto implements Serializable {

    private String projectName;
    private String focus;
    private Set<MemberDto> members;
    private Set<SprintDto> sprint;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public Set<MemberDto> getMembers() {
        return members;
    }

    public void setMembers(Set<MemberDto> members) {
        this.members = members;
    }

    public Set<SprintDto> getSprint() {
        return sprint;
    }

    public void setSprint(Set<SprintDto> sprint) {
        this.sprint = sprint;
    }
}
