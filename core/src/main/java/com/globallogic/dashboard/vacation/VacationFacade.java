package com.globallogic.dashboard.vacation;

import com.globallogic.dashboard.event.VacationData;

import java.util.Date;
import java.util.List;

public interface VacationFacade {

    List<VacationData> getAllVacations();
    List<VacationDto> getVacationByMemberName(String name);
    List<VacationDto> getVacationByTeam(Long teamid);
    List<VacationDto> getVacationByMonth(Date startDate, Date endDate);
    List<VacationDto> getAllVacationBySprint(String sprint);
}
