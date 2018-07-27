package com.globallogic.dashboard.sprint;

import com.google.common.collect.Range;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Sprint implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Date start;

    private Date end;

    private String name;

    private Range<Integer> range;

    @OneToOne
    private SprintDataModel sprintDataModel;




    public Range<Integer> getRange() {
        return range;
    }

    public SprintDataModel getSprintDataModel() {
        return sprintDataModel;
    }

    public void setSprintDataModel(SprintDataModel sprintDataModel) {
        this.sprintDataModel = sprintDataModel;
    }

    public void setRange(Range<Integer> range) {
        this.range = range;
    }

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
