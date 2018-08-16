package com.globallogic.dashboard.vacation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("vacation")
public class VacationResource {

    private VacationFacade vacationFacade;

    public VacationResource(VacationFacade vacationFacade) {
        this.vacationFacade = vacationFacade;
    }

    @GetMapping("loadVacationData")
    public void loadVacation(){
        vacationFacade.loadVacation();
    }

    @GetMapping
    public List<VacationDto> getVacations(@RequestParam(required = false) String memberId,
                                          @RequestParam(required = false)String teamId,
                                          @RequestParam(required = false)String sprintId) {
        VacationFilterDto vacationFilterDto = new VacationFilterDto();
        vacationFilterDto.setMemberId(memberId);
        vacationFilterDto.setSprintId(sprintId);
        vacationFilterDto.setTeamId(teamId);
        return vacationFacade.getVacations(vacationFilterDto);
    }

}
