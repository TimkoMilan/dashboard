package com.globallogic.dashboard.team;

import com.globallogic.dashboard.member.Member;
import com.globallogic.dashboard.sprint.SprintData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Team implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String projectName;

    private String focus;
    
    @OneToMany(mappedBy = "team")
    private Set<Member> members;

    @OneToMany
    private Set<SprintData> sprintData;

    public Set<SprintData> getSprintData() {
        return sprintData;
    }

    public void setSprintData(Set<SprintData> sprintData) {
        this.sprintData = sprintData;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public Long getId() {
        return id;
    }

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
}
