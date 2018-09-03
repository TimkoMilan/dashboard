package com.globallogic.dashboard.event;

import java.util.*;

public class MonthUtil {

    private List<Object> days;

    private Map<String, Integer> monthMap = new HashMap<>();

    private List<MonthData> monthData = new ArrayList<>();

    private Integer monthYear;

    public MonthUtil(List<MonthData> monthData) {
        this.monthData = monthData;
        monthMap.put("january", 1);
        monthMap.put("february", 2);
        monthMap.put("march", 3);
        monthMap.put("april", 4);
        monthMap.put("may", 5);
        monthMap.put("june", 6);
        monthMap.put("july", 7);
        monthMap.put("august", 8);
        monthMap.put("september", 9);
        monthMap.put("october", 10);
        monthMap.put("november", 11);
        monthMap.put("december", 12);
    }

    public void setDays(List<Object> days) {
        this.days = days;
    }

    public void setMonthYear(Integer monthYear) {
        this.monthYear = monthYear;
    }

    public Date monthById(Integer id) {
        for (MonthData monthDatum : monthData) {
            if (monthDatum.getRange().contains(id)) {
                Calendar instance = Calendar.getInstance();
                try {
                    instance.set( monthYear, monthMap.get(monthDatum.getMonth().toLowerCase()) - 1, Integer.valueOf(days.get(id).toString()) + 1);
                } catch (Exception e) {
                    return createExceptionDataAndThrow(id, monthDatum, e);
                }
                return instance.getTime();
            }
        }
        throw new DataProcessorException("No month found for id:" + id);
    }


    private Date createExceptionDataAndThrow(Integer id, MonthData monthData, Exception e) {
        String dayInfo = " days data: ";
        if (days == null) {
            dayInfo += "null!!!";
        } else {
            if (id > days.size()) {
                dayInfo += "id is bigger than days size: " + days.size();
            }
        }
        throw new DataProcessorException("error for id:" + id + " and month value:" + monthData.getMonth() + dayInfo, e);
    }
}
