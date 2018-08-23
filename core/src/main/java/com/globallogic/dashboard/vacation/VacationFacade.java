package com.globallogic.dashboard.vacation;

import java.util.List;

public interface VacationFacade {
    void loadVacation();

    List<VacationDto> getVacations(VacationFilterDto vacationFilterDto);

}
