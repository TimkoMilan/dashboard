package com.globallogic.dashboard.fte;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class FteCreateDto implements Serializable {

    @NotNull
    private Long teamId;

    @NotNull
    private Byte month;

    @NotNull
    private Double fte;

    @NotNull
    private Integer year;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Byte getMonth() {
        return month;
    }

    public void setMonth(Byte month) {
        this.month = month;
    }

    public Double getFte() {
        return fte;
    }

    public void setFte(Double fte) {
        this.fte = fte;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
