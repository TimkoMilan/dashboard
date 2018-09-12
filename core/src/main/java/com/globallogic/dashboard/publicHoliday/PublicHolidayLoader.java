package com.globallogic.dashboard.publicHoliday;

import com.globallogic.dashboard.PublicHoliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PublicHolidayLoader {

    @Autowired
    private PublicHoliday publicHoliday;

    //TODO improve so not to call the method every time
    private List<String> getPublicHolidaysInYearList(int yearInt){
        String holidayData = publicHoliday.getData();
        List<String> publicHolidayList = new ArrayList<String>(Arrays.asList(holidayData.split(",")));
        String easterDateString = publicHoliday.getEaster().get(String.valueOf(yearInt));
        List<String> easterDateForYearList = new ArrayList<String>(Arrays.asList(easterDateString.split(",")));
        publicHolidayList.addAll(easterDateForYearList);
        return publicHolidayList;
    }

    private Boolean isPublicHoliday(int year, int month, int date){
        List<String> publicHolidaysInAYearList = getPublicHolidaysInYearList(year);
        for (String s : publicHolidaysInAYearList) {
            String[] splitDate = s.split("-");
            if (Integer.parseInt(splitDate[0]) == month && Integer.parseInt(splitDate[1]) == date) {
                return true;
            }
        }
        return false;
    }

    public int getWorkingDaysInMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        int counter = 0;
        for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
            DayOfWeek dayOfWeek = yearMonth.atDay(i).getDayOfWeek();
            if (!dayOfWeek.equals(DayOfWeek.SATURDAY) && !dayOfWeek.equals(DayOfWeek.SUNDAY) && !isPublicHoliday(year, month, i)) {
                counter++;
            }
        }
        return counter;
    }

}