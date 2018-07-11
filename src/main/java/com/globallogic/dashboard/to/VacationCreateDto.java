package com.globallogic.dashboard.to;

import java.io.Serializable;
import java.util.Date;

public class VacationCreateDto implements Serializable {

    private String start;

    private String end;

    private String memberName;


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
