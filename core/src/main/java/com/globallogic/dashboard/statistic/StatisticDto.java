package com.globallogic.dashboard.statistic;

import com.globallogic.dashboard.vacation.VacationDto;

import java.util.List;

public class StatisticDto {

    private int workingDays;

    private List<VacationDto> vacationDto;

    private int month;

    private int year;

    private String teamId;

    public int getWorkingDays() {
        return workingDays;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public List<VacationDto> getVacationDto() {
        return vacationDto;
    }

    public void setVacationDto(List<VacationDto> vacationDto) {
        this.vacationDto = vacationDto;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
