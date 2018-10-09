package com.globallogic.dashboard.sprint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class Sprint implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;

    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "sprint")
    private Set<SprintData> sprintData;


    public Set<SprintData> getSprintData() {
        return sprintData;
    }

    public void setSprintData(Set<SprintData> sprintData) {
        this.sprintData = sprintData;
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
