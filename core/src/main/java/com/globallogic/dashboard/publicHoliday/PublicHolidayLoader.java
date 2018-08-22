package com.globallogic.dashboard.publicHoliday;

import com.globallogic.dashboard.PublicHoliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.YearMonth;

@Component
public class PublicHolidayLoader {

    @Autowired
    private PublicHoliday publicHoliday;

      private int getPublicHolidaysInMonth(int yearInt, int monthInt) {
        String holidayData = publicHoliday.getData();
        String[] holiday = holidayData.split(",");
        int counter = 0;
        for (String s : holiday) {
            String[] splitDate = s.split("-");
            if (Integer.parseInt(splitDate[0]) == monthInt) {
                counter++;
            }

        }
        String easter = publicHoliday.getEaster().get(String.valueOf(yearInt));
        String[] splitEaster = easter.split("-");
        if (monthInt == Integer.parseInt(splitEaster[0])) {
            counter++;
        }
        return counter;
    }

      public int getWorkingDaysInMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        int counter = 0;
        for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
            DayOfWeek dayOfWeek = yearMonth.atDay(i).getDayOfWeek();
            if (!dayOfWeek.equals(DayOfWeek.SATURDAY) && !dayOfWeek.equals(DayOfWeek.SUNDAY)) {
                counter++;
            }
        }
        return counter;
    }

     int getWorkingFond(int year, int month) {
        return getWorkingDaysInMonth(year, month) - getPublicHolidaysInMonth(year, month);
    }


}