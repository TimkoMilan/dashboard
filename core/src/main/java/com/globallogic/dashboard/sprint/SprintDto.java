package com.globallogic.dashboard.sprint;

import java.io.Serializable;
import java.util.Date;

public class SprintDto implements Serializable {

    private Date start;

    private Date end;

    private String name;


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

    public String getName(Object o) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
