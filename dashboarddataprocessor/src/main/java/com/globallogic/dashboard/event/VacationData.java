package com.globallogic.dashboard.event;

import java.util.Date;

public class VacationData {
    private String name;
    private Date from;
    private Date to;
    private boolean isHalfDay;


    public String getName() {
        return name;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public boolean isHalfDay() {
        return isHalfDay;
    }

    public void setHalfDay(boolean halfDay) {
        isHalfDay = halfDay;
    }

    public VacationData(String name, Date from, Date to,boolean isHalfDay) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.isHalfDay = isHalfDay;
    }

    @Override
    public String toString() {
        return "VacationData{" +
                "name='" + name + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", isHalfDay=" + isHalfDay +
                '}';
    }
}
