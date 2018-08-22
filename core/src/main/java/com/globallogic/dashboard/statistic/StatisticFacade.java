package com.globallogic.dashboard.statistic;

import com.globallogic.dashboard.event.MonthData;
import com.globallogic.dashboard.publicHoliday.PublicHolidayLoader;
import com.globallogic.dashboard.vacation.VacationDto;
import com.globallogic.dashboard.vacation.VacationFilterDto;
import com.globallogic.dashboard.vacation.VacationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
//
//
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
                String startDateString = yearInt + "-" + monthInt + "-01";
                String endDateString = yearInt + "-" + monthInt + "-" + lastDayInmonth;
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date startDate = df.parse(startDateString);
                    Date endDate = df.parse(endDateString);
                    vacationFilterDto.setStart(startDate);
                    vacationFilterDto.setEnd(endDate);
                    vacationFilterDto.setTeamId(tId);
                    List<VacationDto> vacations = vacationService.getVacations(vacationFilterDto);
                    statisticDto.setVacationDto(vacations);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                statisticDtos.add(statisticDto);

//
//
            }
        }
        return statisticDtos;
    }
}
