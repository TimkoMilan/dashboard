package com.globallogic.dashboard.team;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globallogic.dashboard.fte.Fte;
import com.globallogic.dashboard.member.Member;
import com.globallogic.dashboard.sprint.SprintData;

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

    @JsonIgnore
    @OneToMany(mappedBy = "team")
    private Set<SprintData> sprintDatumModels;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private Set<Fte> fte;


    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public Set<SprintData> getSprintDatumModels() {
        return sprintDatumModels;
    }

    public void setSprintDatumModels(Set<SprintData> sprintDatumModels) {
        this.sprintDatumModels = sprintDatumModels;
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

    public Set<Fte> getFte() {
        return fte;
    }

    public void setFte(Set<Fte> fte) {
        this.fte = fte;
    }
}
