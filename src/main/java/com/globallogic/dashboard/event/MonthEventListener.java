package com.globallogic.dashboard.event;

import com.globallogic.dashboard.MonthDto;
import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.List;

public class MonthEventListener implements EventListener {
    public static final int DECEMBER_OFFSET = 30;
    private int start;
    private int end;
    private String monthName;

    private List<String> data = new ArrayList<>();

    private List<MonthDto> monthDtos = new ArrayList<>();

    public List<MonthDto> getMonthDtos() {
        return monthDtos;
    }

    public void fireEvent(Event event) {
        if (event instanceof StartEvent) {
            data = new ArrayList<>();
            start = ((StartEvent) event).getStart();
            monthName = event.getPayload();
        } else if (event instanceof EndEvent) {
            end = ((EndEvent) event).getEnd();
            monthDtos.add(new MonthDto(monthName, Range.between(start, end), data));
        } else if (event instanceof FinishEvent) {
            end = ((FinishEvent) event).getEnd();
            monthDtos.add(new MonthDto(monthName, Range.between(start, end + DECEMBER_OFFSET), data));
            for (MonthDto monthDto : monthDtos) {
                //System.out.println(monthDto);
            }
        } else data.add(event.getPayload());
    }
}
