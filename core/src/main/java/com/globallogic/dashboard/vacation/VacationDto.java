package com.globallogic.dashboard.vacation;

import java.io.Serializable;
import java.util.Date;

public class VacationDto implements Serializable {

    private String name;
    private Date from;
    private boolean isHalfDay;


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

    public void setName(String name) {
        this.name = name;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public VacationDto(String name, Date from, boolean isHalfDay) {
        this.name = name;
        this.from = from;
        this.isHalfDay = isHalfDay;
    }

    public VacationDto() {
    }

    @Override
    public String toString() {
        return "VacationDto{" +
                "name='" + name + '\'' +
                ", from=" + from +
                ", isHalfDay=" + isHalfDay +
                '}';
    }
}
