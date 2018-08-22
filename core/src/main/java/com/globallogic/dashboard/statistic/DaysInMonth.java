package com.globallogic.dashboard.statistic;

import org.springframework.stereotype.Component;

@Component
public class DaysInMonth {

    public int daysByMonth(int month){
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 )
        {
            return 31;
        }
        else if ( month == 4 || month == 6 || month == 9 || month == 11 )
        {
            return 30;
        }
        else if ( month == 2 )
        {
           return 28;
        }
        return 0;
    }
}
