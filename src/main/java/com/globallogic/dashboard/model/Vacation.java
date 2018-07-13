package com.globallogic.dashboard.model;

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

    private String start;

    private String end;

    private String month;

    public Vacation(){}

    public Vacation(String name, String month, String start, String end){
        this.member = new Member();
        this.member.setName(name);
        this.month = month;
        this.start = start;
        this.end = end;
    }


    @ManyToOne
    private Member member;

    public Long getId() {
        return id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
