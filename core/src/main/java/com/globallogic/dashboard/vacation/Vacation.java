package com.globallogic.dashboard.vacation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globallogic.dashboard.member.Member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Vacation implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Date start;

    private boolean isHalfDay;

    @ManyToOne
    @JsonIgnore
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public boolean isHalfDay() {
        return isHalfDay;
    }

    public void setHalfDay(boolean halfDay) {
        isHalfDay = halfDay;
    }


}
