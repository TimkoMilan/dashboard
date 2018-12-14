package com.globallogic.dashboard.event;

import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.List;

public class MonthEventListener implements EventListener<String> {
    public static final int DECEMBER_OFFSET = 30;
    private int start;
    private int end;

    private String monthName;

    private List<String> data = new ArrayList<>();

    private List<MonthData> monthData = new ArrayList<>();

    public List<MonthData> getMonthData() {
        return monthData;
    }

    public List<String> getData(){
        return data;
    }

    public int getEnd (){
        return end;
    }
    public int getStart(){
        return start;
    }


    public void fireEvent(Event<String> event) {
        if (event instanceof StartEvent) {
            data = new ArrayList<>();
            start = ((StartEvent) event).getStart();
            monthName = event.getPayload();
        } else if (event instanceof EndEvent) {
            end = ((EndEvent) event).getEnd();
            monthData.add(new MonthData(monthName, Range.between(start, end), data));
        } else if (event instanceof FinishEvent) {
            end = ((FinishEvent) event).getEnd();
            monthData.add(new MonthData(monthName, Range.between(start, end + DECEMBER_OFFSET), data));
        } else data.add(event.getPayload());
    }
}
