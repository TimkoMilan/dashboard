package com.globallogic.dashboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Vacation {

    @Id
    @GeneratedValue
    private Long id;

    private Date start;

    private Date end;


    public Vacation() {
    }

    @ManyToOne
    @JsonIgnore //TODO update either to DTo, or create unidirectional mapping
    private Member member;

    public Long getId() {
        return id;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
