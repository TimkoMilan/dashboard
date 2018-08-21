package com.globallogic.dashboard.scheduler;

import com.globallogic.dashboard.sprint.SprintDataFacade;
import com.globallogic.dashboard.vacation.VacationFacade;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final VacationFacade vacationFacade;
    private final SprintDataFacade sprintDataFacade;

    public ScheduledTasks(final VacationFacade vacationFacade, final SprintDataFacade sprintDataFacade) {
        this.vacationFacade = vacationFacade;
        this.sprintDataFacade = sprintDataFacade;
    }

    @Scheduled(cron = "${cron.dataLoader.expression}")
    public void dataLoaderScheduledTask(){
        vacationFacade.loadVacation();
        sprintDataFacade.loadSprintData();
    }

}
