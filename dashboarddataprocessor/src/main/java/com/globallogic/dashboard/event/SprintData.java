package com.globallogic.dashboard.event;

import org.apache.commons.lang3.Range;

public class SprintData {

    private String name;
    private String Team;
    private Long taken;
    private Long completed;


    public String getTeam() {
        return Team;
    }

    public void setTeam(String team) {
        Team = team;
    }

    public Long getTaken() {
        return taken;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }

    public Long getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SprintData{" +
                "name='" + name + '\'' +
                '}';
    }
}
