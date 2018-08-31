package com.globallogic.dashboard.event;

import java.util.Date;

public class VacationData {
    private String name;
    private Date from;
    private boolean isHalfDay;
    private String teamName;
    private String position;


    public String getName() {
        return name;
    }

    public Date getFrom() {
        return from;
    }


    public boolean isHalfDay() {
        return isHalfDay;
    }

    public void setHalfDay(boolean halfDay) {
        isHalfDay = halfDay;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getPosition() {
        return position;
    }

    public VacationData(String name, Date from, boolean isHalfDay, String teamName, String position) {
        this.name = name;
        this.from = from;
        this.isHalfDay = isHalfDay;
        this.teamName = teamName;
        this.position = position;
    }

    @Override
    public String toString() {
        return "VacationData{" +
                "name='" + name + '\'' +
                ", from=" + from +
                ", isHalfDay=" + isHalfDay +
                ", teamName='" + teamName + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
