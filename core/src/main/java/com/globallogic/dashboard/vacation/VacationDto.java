package com.globallogic.dashboard.vacation;

import java.io.Serializable;
import java.util.Date;

public class VacationDto implements Serializable{
    private String name;
    private Date from;
    private Date to;
    private boolean isHalfDay;

    public VacationDto() {
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public VacationDto(String name, Date from, Date to,boolean isHalfDay) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.isHalfDay = isHalfDay;
    }

    @Override
    public String toString() {
        return "VacationDto{" +
                "name='" + name + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", isHalfDay=" + isHalfDay +
                '}';
    }
}
