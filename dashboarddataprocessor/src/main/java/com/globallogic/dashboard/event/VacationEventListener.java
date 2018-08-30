package com.globallogic.dashboard.event;


import java.util.ArrayList;
import java.util.List;


public class VacationEventListener implements EventListener<String>{
    private int start;
    boolean isHalfDay = false;


    private List<VacationData> vacationData = new ArrayList<>();

    private MonthUtil monthUtil;

    public VacationEventListener(MonthUtil monthUtil) {
        this.monthUtil = monthUtil;
    }

    public void fireEvent(Event<String> event) {
        List context = (List) event.getContext();
        if (event instanceof StartEvent) {
            start = ((StartEvent) event).getStart();
            isHalfDay = isHalfDay(event.getPayload());
        } else if (event instanceof EndEvent) {
            String userName = context.get(0).toString();
            vacationData.add(new VacationData(userName, monthUtil.monthById(start - 3), isHalfDay));
        } else if (event instanceof FinishEvent) {
            String userName = context.get(0).toString();
            vacationData.add(new VacationData(userName, monthUtil.monthById(start - 3), isHalfDay));
        }

    }

    private boolean isHalfDay(String rowdata) {
        if (rowdata.equals("0.5")) {
            return true;
        }
        return false;
    }


    public List<VacationData> getVacationData() {
        return vacationData;
    }
}
