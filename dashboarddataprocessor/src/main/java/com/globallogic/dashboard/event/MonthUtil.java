package com.globallogic.dashboard.event;

import java.util.*;

public class MonthUtil {
    private List<Object> days; //todo ctor injection!!!

    private Map<String, Integer> monthMap = new HashMap<>();

    private List<MonthData> monthData = new ArrayList<>();

    public MonthUtil(List<MonthData> monthData) {
        this.monthData = monthData;
        monthMap.put("January", 1);
        monthMap.put("February", 2);
        monthMap.put("March", 3);
        monthMap.put("April", 4);
        monthMap.put("May", 5);
        monthMap.put("June", 6);
        monthMap.put("July", 7);
        monthMap.put("August", 8);
        monthMap.put("September", 9);
        monthMap.put("October", 10);
        monthMap.put("November", 11);
        monthMap.put("December", 12);
    }

    public void setDays(List<Object> days) {
        this.days = days;
    }

    public Date monthById(Integer id) {
        for (MonthData monthDatum : monthData) {
            if (monthDatum.getRange().contains(id)) {
                Calendar instance = Calendar.getInstance();
                try {
                    instance.set(2018, monthMap.get(monthDatum.getMonth()) - 1, Integer.valueOf(days.get(id).toString()) + 1);
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
