package com.globallogic.dashboard.to;

import com.globallogic.dashboard.model.Vacation;

import java.io.Serializable;

public class VacationCreateDto implements Serializable {

    private String start;

    private String end;

    private String month;

    private String memberName;

    public VacationCreateDto(Vacation vacation){
        this.memberName = vacation.getMember().getName();
        this.month = vacation.getMonth();
        this.start = vacation.getStart();
        this.end = vacation.getEnd();
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public void setEnd(String end) {
        this.end = end;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "VacationCreateDto{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", memberName='" + memberName + '\'' +
                '}';
    }
}
