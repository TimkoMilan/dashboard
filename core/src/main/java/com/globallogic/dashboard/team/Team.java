package com.globallogic.dashboard.team;

import com.globallogic.dashboard.member.Member;
import com.globallogic.dashboard.sprint.SprintDataModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Team implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String focus;
    
    @OneToMany(mappedBy = "team")
    private Set<Member> members;

    @OneToMany(mappedBy = "team")
    private Set<SprintDataModel> sprintDatumModels;


    public void setSprintDatumModels(Set<SprintDataModel> sprintDatumModels) {
        this.sprintDatumModels = sprintDatumModels;
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
