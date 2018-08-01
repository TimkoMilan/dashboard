package com.globallogic.dashboard.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globallogic.dashboard.team.Team;
import com.globallogic.dashboard.vacation.Vacation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Member implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String position;

    private String billingValue;

    private String focus;

    private String searchString;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "member")
    private Set<Vacation> vacations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBillingValue() {
        return billingValue;
    }

    public void setBillingValue(String billingValue) {
        this.billingValue = billingValue;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<Vacation> getVacations() {
        return vacations;
    }

    public void setVacations(Set<Vacation> vacations) {
        this.vacations = vacations;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}
