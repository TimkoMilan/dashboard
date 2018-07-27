package com.globallogic.dashboard.event;

public class SprintData {

    private String name;
    private String Team;
    private String taken;
    private String completed;


    public String getTaken() {
        return taken;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public void setTeam(String team) {
        Team = team;
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
                ", Team='" + Team + '\'' +
                ", taken='" + taken + '\'' +
                ", completed='" + completed + '\'' +
                '}';
    }


    public String getTeam() {
        return Team;
    }
}
