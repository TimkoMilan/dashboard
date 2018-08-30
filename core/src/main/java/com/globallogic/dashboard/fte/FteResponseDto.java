package com.globallogic.dashboard.fte;

public class FteResponseDto {

    private Long teamId;
    private Byte month;
    private Double fte;
    private Integer year;


    public Long getTeamId() {
        return teamId;
    }

    public FteResponseDto setTeamId(Long teamId) {
        this.teamId = teamId;
        return this;
    }

    public Byte getMonth() {
        return month;
    }

    public FteResponseDto setMonth(Byte month) {
        this.month = month;
        return this;
    }

    public Double getFte() {
        return fte;
    }

    public Integer getYear() {
        return year;
    }

    public FteResponseDto setYear(Integer year) {
        this.year = year;
        return this;
    }

    public FteResponseDto setFte(Double fte) {
        this.fte = fte;
        return this;
    }
}
