package com.globallogic.dashboard.event;


import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

public class VacationEventListener implements EventListener<String>{

    private int start;
    boolean isHalfDay = false;
    private Integer offset;
    private String teamString = "";

    private List<VacationData> vacationData = new ArrayList<>();

    private MonthUtil monthUtil;

    private Integer positionIndex = 2;


    public VacationEventListener(MonthUtil monthUtil, Integer offset, Integer positionIndex) {
        this.monthUtil = monthUtil;
        this.offset = offset;
        if(positionIndex != null){
            this.positionIndex = positionIndex;
        }
    }

    public void fireEvent(Event<String> event) {
        List context = (List) event.getContext();
        if (event instanceof StartEvent) {
            start = ((StartEvent) event).getStart();
            isHalfDay = isHalfDay(event.getPayload());
        } else if (event instanceof EndEvent) {
            String teamName = context.get(0).toString();
            String userName = context.get(1).toString();
            String position = context.get(positionIndex).toString();
            if(!Strings.isNullOrEmpty(teamName)){
                teamString = teamName;
            }
            vacationData.add(new VacationData(userName, monthUtil.monthById(start - offset), isHalfDay, teamString, position));
        } else if (event instanceof FinishEvent) {
            String teamName = context.get(0).toString();
            String userName = context.get(1).toString();
            String position = context.get(positionIndex).toString();
            if(!Strings.isNullOrEmpty(teamName)){
                teamString = teamName;
            }
            vacationData.add(new VacationData(userName, monthUtil.monthById(start - offset), isHalfDay, teamString, position));
        }
    }

    private boolean isHalfDay(String rowdata) {    // TODO there are values that are not 0.5, e.g. 0.3,0.4 in the sheets
        if (rowdata.equals("0.5")) {
            return true;
        }
        return false;
    }


    public List<VacationData> getVacationData() {
        return vacationData;
    }
}
