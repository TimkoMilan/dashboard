package com.globallogic.dashboard.statistic;

import com.globallogic.dashboard.publicHoliday.PublicHolidayLoader;
import com.globallogic.dashboard.vacation.VacationDto;
import com.globallogic.dashboard.vacation.VacationFilterDto;
import com.globallogic.dashboard.vacation.VacationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StatisticFacade {

    private VacationService vacationService;
    private PublicHolidayLoader publicHolidayLoader;
    private DaysInMonth daysInMonth;

    public StatisticFacade(VacationService vacationService, PublicHolidayLoader publicHolidayLoader, DaysInMonth daysInMonth) {
        this.vacationService = vacationService;
        this.publicHolidayLoader = publicHolidayLoader;
        this.daysInMonth = daysInMonth;
    }

    public List<StatisticDto> getStatistic(String year, String month, String teamId) {
        String[] years = year.split(",");
        String[] months = month.split(",");
        String[] teamsId = teamId.split(",");
        List<StatisticDto> statisticDtos = new ArrayList<>();

        for (String tId : teamsId) {
            for (String month1 : months) {

                int yearInt = Integer.parseInt(year);
                int teamInt = Integer.parseInt(tId);
                int monthInt = Integer.parseInt(month1);
                int workingDays = publicHolidayLoader.getWorkingDaysInMonth(yearInt, monthInt);

                VacationFilterDto vacationFilterDto = new VacationFilterDto();
                StatisticDto statisticDto = new StatisticDto();
                statisticDto.setWorkingDays(workingDays);
                statisticDto.setYear(yearInt);
                statisticDto.setMonth(monthInt);
                statisticDto.setTeamId(tId);

                int lastDayInmonth = daysInMonth.daysByMonth(monthInt);
                Calendar startDate = Calendar.getInstance();
                startDate.set(Calendar.YEAR, yearInt);
                startDate.set(Calendar.MONTH, monthInt);
                startDate.set(Calendar.DAY_OF_MONTH, 1);
                Date startDates = startDate.getTime();

                Calendar endDate = Calendar.getInstance();
                endDate.set(Calendar.YEAR, yearInt);
                endDate.set(Calendar.MONTH, monthInt);
                endDate.set(Calendar.DAY_OF_MONTH, lastDayInmonth);
                Date endDates = endDate.getTime();

                vacationFilterDto.setStart(startDates);
                vacationFilterDto.setEnd(endDates);
                vacationFilterDto.setTeamId(tId);
                List<VacationDto> vacations = vacationService.getVacations(vacationFilterDto);
                statisticDto.setVacationDto(vacations);

                statisticDtos.add(statisticDto);
            }
        }
        return statisticDtos;
    }
}
