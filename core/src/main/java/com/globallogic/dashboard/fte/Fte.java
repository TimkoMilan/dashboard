package com.globallogic.dashboard.fte;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globallogic.dashboard.team.Team;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Fte implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    private Byte month;

    private Integer year;

    private Double fte;


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Byte getMonth() {
        return month;
    }

    public void setMonth(Byte month) {
        this.month = month;
    }

    public Double getFte() {
        return fte;
    }

    public void setFte(Double fte) {
        this.fte = fte;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
