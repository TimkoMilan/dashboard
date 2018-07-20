package com.globallogic.dashboard.event;

import com.globallogic.dashboard.MonthUtil;
import com.globallogic.dashboard.VacationDto;
import com.globallogic.dashboard.service.DataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class VacationEventListener implements EventListener, DataLoader {
    private int start;
    private int end;
    boolean isHalfDay = false;

    private static final Logger log = LoggerFactory.getLogger(VacationEventListener.class);

    private List<VacationDto> vacationDtos = new ArrayList<>();

    private MonthUtil monthUtil;

    public VacationEventListener(MonthUtil monthUtil) {
        this.monthUtil = monthUtil;
    }

    public void fireEvent(Event event) {
        List context = (List) event.getContext();
        if (event instanceof StartEvent) {
            start = ((StartEvent) event).getStart();
            isHalfDay = isHalfDay(event.getPayload());
        } else if (event instanceof EndEvent) {
            String userName = context.get(0).toString();
            end = ((EndEvent) event).getEnd();
            vacationDtos.add(new VacationDto(userName, monthUtil.monthById(start - 2), monthUtil.monthById(end - 2), isHalfDay));
        } else if (event instanceof FinishEvent) {
            String userName = context.get(0).toString();
            end = ((FinishEvent) event).getEnd();
            vacationDtos.add(new VacationDto(userName, monthUtil.monthById(start - 2), monthUtil.monthById(end - 2), isHalfDay));
        }

    }

    private boolean isHalfDay(String rowdata) {
        if (rowdata.equals("0.5")) {
            return true;
        }
        return false;
    }


    public List<VacationDto> getVacationDtos() {
        return vacationDtos;
    }

    @Override
    public void loadData() {
        return;
    }

    @Override
    public List<VacationDto> loadVacationData() {
        return null;
    }


}
