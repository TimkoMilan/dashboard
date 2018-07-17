package com.globallogic.dashboard;

import java.io.Serializable;
import java.util.Date;

public class VacationDto implements Serializable{
    private String name;
    private Date from;
    private Date to;

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

    public VacationDto(String name, Date from, Date to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "VacationDto{" +
                "name='" + name + '\'' +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
