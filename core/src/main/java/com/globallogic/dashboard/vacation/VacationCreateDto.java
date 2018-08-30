package com.globallogic.dashboard.vacation;

import java.io.Serializable;
import java.util.Date;

public class VacationCreateDto implements Serializable {

    private Date start;


    private String month;

    private String memberName;

    public VacationCreateDto(Vacation vacation) {
        this.memberName = vacation.getMember().getName();
        this.start = vacation.getStart();
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
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
                ", memberName='" + memberName + '\'' +
                '}';
    }
}
