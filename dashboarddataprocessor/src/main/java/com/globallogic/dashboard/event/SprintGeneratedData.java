package com.globallogic.dashboard.event;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

public class SprintGeneratedData {

    private String name;
    private String teamName;

    private float taken;
    private float completed;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public float getTaken() {
        return taken;
    }

    public void setTaken(float taken) {
        this.taken = taken;
    }

    public float getCompleted() {
        return completed;
    }

    public void setCompleted(float completed) {
        this.completed = completed;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "SprintGeneratedData{" +
                "name='" + name + '\'' +
                ", teamName='" + teamName + '\'' +
                ", taken='" + taken + '\'' +
                ", completed='" + completed + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintGeneratedData that = (SprintGeneratedData) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(teamName, that.teamName) &&
                Objects.equals(taken, that.taken) &&
                Objects.equals(completed, that.completed) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, teamName, taken, completed, start, end);
    }
}
