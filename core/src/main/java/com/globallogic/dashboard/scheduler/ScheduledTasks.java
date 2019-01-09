package com.globallogic.dashboard.scheduler;

import com.globallogic.dashboard.sprint.SprintDataFacade;
import com.globallogic.dashboard.vacation.VacationFacade;
import com.globallogic.dashboard.validationToken.TokenService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final VacationFacade vacationFacade;
    private final SprintDataFacade sprintDataFacade;
    private final TokenService tokenService;

    public ScheduledTasks(final VacationFacade vacationFacade, final SprintDataFacade sprintDataFacade, TokenService tokenService) {
        this.vacationFacade = vacationFacade;
        this.sprintDataFacade = sprintDataFacade;
        this.tokenService = tokenService;
    }

    @Scheduled(cron = "${cron.dataLoader.expression}")
    public void dataLoaderScheduledTask(){
        vacationFacade.loadVacation();
        sprintDataFacade.loadSprintData();
    }
    @Scheduled(cron ="${cron.dataLoader.expression}")
    public void checkTokenValidation(){
        tokenService.checkTokenValidation();
    }


}
