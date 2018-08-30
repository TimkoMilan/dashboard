package com.globallogic.dashboard.statistic;

import com.globallogic.dashboard.fte.FteFacade;
import com.globallogic.dashboard.fte.FteService;
import com.globallogic.dashboard.publicHoliday.PublicHolidayLoader;
import com.globallogic.dashboard.team.TeamService;
import com.globallogic.dashboard.vacation.Vacation;
import com.globallogic.dashboard.vacation.VacationDto;
import com.globallogic.dashboard.vacation.VacationFilterDto;
import com.globallogic.dashboard.vacation.VacationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class StatisticFacade {

    private VacationService vacationService;
    private PublicHolidayLoader publicHolidayLoader;
    private DaysInMonth daysInMonth;
    private FteService fteService;
    private TeamService teamService;
    private FteFacade fteFacade;

    public StatisticFacade(VacationService vacationService, PublicHolidayLoader publicHolidayLoader, DaysInMonth daysInMonth, FteService fteService, TeamService teamService, FteFacade fteFacade) {
        this.vacationService = vacationService;
        this.publicHolidayLoader = publicHolidayLoader;
        this.daysInMonth = daysInMonth;
        this.fteService = fteService;
        this.teamService = teamService;
        this.fteFacade = fteFacade;
    }

    public List<StatisticDto> getStatistic(String year, String month, String teamId) {
        if (teamId.equals("-1")){
            teamId="44,45,46,47,48";
        }

        String[] years = year.split(",");
        String[] months = month.split(",");
        String[] teamsId = teamId.split(",");
        List<StatisticDto> statisticDtos = new ArrayList<>();

        for (String tId : teamsId)
            for (String month1 : months) {

                int yearInt = Integer.parseInt(year);
                int teamInt = Integer.parseInt(tId);
                int monthInt = Integer.parseInt(month1);
                // increase month by 1 since frontend starts counting months from 0
                int workingDays = publicHolidayLoader.getWorkingDaysInMonth(yearInt, monthInt + 1);

                VacationFilterDto vacationFilterDto = new VacationFilterDto();
                StatisticDto statisticDto = new StatisticDto();
                statisticDto.setWorkingDays(workingDays);
                statisticDto.setYear(yearInt);
                statisticDto.setMonth(monthInt);
                statisticDto.setTeamId(tId);

                int lastDayInmonth = daysInMonth.daysByMonth(monthInt);//TODO UTIL and service FILTER
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

                byte monthByte = (byte) monthInt;
                Long teamIdLong = new Long(teamInt);
                if (fteFacade.findFteByTeamAndMonth(monthByte, teamIdLong)!=null){
                    statisticDto.setFte(fteFacade.findFteByTeamAndMonth(monthByte, teamIdLong));
                }else {
                    statisticDto.setFte(0.0);
                }
                statisticDtos.add(statisticDto);

            }
        return statisticDtos;
    }

    //TODO refactor this method
    public List<StatisticDto> getStatisticByDateRange(Date startDate, Date endDate, String teamId){
        List<StatisticDto> statisticDtoList = new ArrayList<>();
        if (teamId.equals("-1")){
            teamId="44,45,46,47,48";
        }
        String[] teamIds = teamId.split(",");
        for(String tId : teamIds){
            VacationFilterDto vacationFilterDto = new VacationFilterDto();
            vacationFilterDto.setTeamId(tId);
            vacationFilterDto.setStart(startDate);
            vacationFilterDto.setEnd(endDate);
            List<VacationDto> vacations = vacationService.getVacations(vacationFilterDto);
            Calendar beginCalendar = Calendar.getInstance();
            Calendar finishCalendar = Calendar.getInstance();
            beginCalendar.setTime(startDate);
            finishCalendar.setTime(endDate);
            while (beginCalendar.before(finishCalendar)){
                StatisticDto statisticDto = new StatisticDto();
                Integer yearInt = beginCalendar.get(Calendar.YEAR);
                Integer monthInt = beginCalendar.get(Calendar.MONTH);
                statisticDto.setWorkingDays(publicHolidayLoader.getWorkingDaysInMonth(
                        yearInt, monthInt+1));
                Long teamIdLong = new Long(tId);
                Double fteForMonth = fteFacade.findFteByTeamAndMonth(
                         monthInt.byteValue(), teamIdLong);
                if(fteForMonth!=null){
                    statisticDto.setFte(fteForMonth);
                }
                else{
                    statisticDto.setFte(0.0);
                }
                statisticDto.setMonth(monthInt);
                statisticDto.setYear(yearInt);
                statisticDto.setTeamId(tId);
                Calendar nextMonth = Calendar.getInstance();
                nextMonth.setTime(beginCalendar.getTime());
                nextMonth.add(Calendar.MONTH, 1);
                List<VacationDto> vacationForMonth = vacations.stream().filter(
                        vacation -> vacation.getFrom().after(beginCalendar.getTime()) &&
                            vacation.getFrom().before(nextMonth.getTime())
                        ).collect(Collectors.toList());
                statisticDto.setVacationDto(vacationForMonth);
                statisticDtoList.add(statisticDto);
                beginCalendar.add(Calendar.MONTH, 1);
            }
        }
        return statisticDtoList;
    }
}
