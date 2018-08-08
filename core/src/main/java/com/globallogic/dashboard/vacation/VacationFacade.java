package com.globallogic.dashboard.vacation;

import java.util.Date;
import java.util.List;

public interface VacationFacade {
    void loadVacation();
    List<VacationDto> getVacations(VacationFilterDto vacationFilterDto);
    List<VacationDto> getVacationByMemberName(String name);
    List<VacationDto> getVacationByTeam(Long teamid);
    List<VacationDto> getVacationByMonth(Date startDate, Date endDate);
    List<VacationDto> getAllVacationBySprint(String sprint);
}
