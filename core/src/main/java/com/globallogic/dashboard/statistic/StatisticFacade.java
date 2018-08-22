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

    public List<StatisticDto> getStatistic(String year, String month) {
        String []years =year.split(",");
        String []months = month.split(",");
        for (String s : years) {
            for (String month1 : months) {
                int yearInt = Integer.parseInt(s);
                int monthInt = Integer.parseInt(month1);



            }
        }


//        int workingDays = publicHolidayLoader.getWorkingDaysInMonth(year, month);
//        List<StatisticDto> statisticDtos = new ArrayList<>();
//        VacationFilterDto vacationFilterDto = new VacationFilterDto();
//        StatisticDto statisticDto = new StatisticDto();
//
//        statisticDto.setWorkingDays(workingDays);
//        statisticDto.setYear(year);
//        statisticDto.setMonth(month);
//        int lastDayInmonth = daysInMonth.daysByMonth(month);
//        String startDateString = year + "-" + month + "-01";
//        String endDateString = year + "-" + month + "-" + lastDayInmonth;
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date startDate = df.parse(startDateString);
//            Date endDate = df.parse(endDateString);
//            vacationFilterDto.setStart(startDate);
//            vacationFilterDto.setEnd(endDate);
//            List<VacationDto> vacations = vacationService.getVacations(vacationFilterDto);
//            statisticDto.setVacationDto(vacations);
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        statisticDtos.add(statisticDto);

        return null;
    }
}
