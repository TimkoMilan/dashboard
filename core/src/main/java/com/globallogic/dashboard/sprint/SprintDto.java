package com.globallogic.dashboard.sprint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class SprintDto implements Serializable {

    private Long id;

    private String name;

    private Date start;

    private Date end;

    @JsonIgnore
    private Set<SprintDataDto> sprintDataDtos;


    public Set<SprintDataDto> getSprintDataDtos() {
        return sprintDataDtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSprintDataDtos(Set<SprintDataDto> sprintDataDtos) {
        this.sprintDataDtos = sprintDataDtos;
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
