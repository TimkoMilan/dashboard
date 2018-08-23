package com.globallogic.dashboard.vacation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("vacation")
public class VacationResource {

    private VacationFacade vacationFacade;

    public VacationResource(VacationFacade vacationFacade) {
        this.vacationFacade = vacationFacade;
    }

    @GetMapping("loadVacationData")
    public void loadVacation() {
        vacationFacade.loadVacation();
    }

    @GetMapping
    public List<VacationDto> getVacations(@RequestParam(required = false) String memberId,
                                          @RequestParam(required = false) String teamId,
                                          @RequestParam(required = false) String sprintId,
                                          @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                  Date startDate,
                                          @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                  Date endDate
    ) {
        VacationFilterDto vacationFilterDto = new VacationFilterDto();

        vacationFilterDto.setMemberId(memberId);
        vacationFilterDto.setSprintId(sprintId);
        vacationFilterDto.setTeamId(teamId);
        vacationFilterDto.setStart(startDate);
        vacationFilterDto.setEnd(endDate);
        return vacationFacade.getVacations(vacationFilterDto);
    }


}
