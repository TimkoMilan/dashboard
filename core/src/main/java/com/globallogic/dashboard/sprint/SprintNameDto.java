package com.globallogic.dashboard.sprint;

import java.io.Serializable;
import java.util.Date;

public class SprintNameDto implements Serializable {

    private Date start;
    private Date end;
    private String sprintName;

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

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }
}
