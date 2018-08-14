package com.globallogic.dashboard.sprint;

import java.io.Serializable;
import java.util.Date;

public class SprintDto implements Serializable {

    private Long id;

    private String name;

    private Date start;

    private Date end;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
