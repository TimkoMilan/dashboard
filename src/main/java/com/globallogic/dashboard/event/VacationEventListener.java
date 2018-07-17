package com.globallogic.dashboard.event;

import com.globallogic.dashboard.MonthUtil;
import com.globallogic.dashboard.VacationDto;
import com.globallogic.dashboard.service.DataLoader;
import com.globallogic.dashboard.to.MemberCreateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;



public class VacationEventListener implements EventListener,DataLoader {
    private int start;
    private int end;
    private String monthName;
    private static final Logger log = LoggerFactory.getLogger(VacationEventListener.class);

    private List<VacationDto> vacationDtos = new ArrayList<>();

    private MonthUtil monthUtil;

    public VacationEventListener(MonthUtil monthUtil) {
        this.monthUtil = monthUtil;
    }

    public void fireEvent(Event event) {
        if (event instanceof StartEvent) {
            start = ((StartEvent) event).getStart();
            monthName = event.getPayload();
        } else if (event instanceof EndEvent) {
            end = ((EndEvent) event).getEnd();
            List context = (List) event.getContext();
            vacationDtos.add(new VacationDto(context.get(0).toString(), monthUtil.monthById(start - 2), monthUtil.monthById(end - 2)));
        } else if (event instanceof FinishEvent) {
            List context = (List) event.getContext();
            end = ((FinishEvent) event).getEnd();
            vacationDtos.add(new VacationDto(context.get(0).toString(), monthUtil.monthById(start - 2), monthUtil.monthById(end - 2)));
        }
    }


    public List<VacationDto> getVacationDtos() {
        return vacationDtos;
    }

    @Override
    public List<MemberCreateDto> loadData() {
        return null;
    }

    @Override
    public List<VacationDto> loadVacationData() {
        return null;
    }


}
