package com.globallogic.dashboard.vacation;

import com.globallogic.dashboard.event.VacationData;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("vacations")
public class VacationResource {

    private VacationFacade vacationFacade;

    public VacationResource(VacationFacade vacationFacade) {
        this.vacationFacade = vacationFacade;
    }

    @GetMapping
    public List<VacationData> getAllVacations() {
       return vacationFacade.getAllVacations();
    }
    @GetMapping("member/{name}")
    public List<VacationDto> getVacationByMember(@PathVariable(value = "name")String name){
        return vacationFacade.getVacationByMemberName(name);
    }

    @GetMapping("/team/{teamid}")
    public List<VacationDto>getVacationByTeam(@PathVariable(value = "teamid")Long teamid){
        return vacationFacade.getVacationByTeam(teamid);
    }

    @GetMapping("/date")
    public List<VacationDto>getVacationByMonth(@RequestParam(value = "startDate")@DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                               @RequestParam(value = "endDate")@DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate){
        return vacationFacade.getVacationbyMonth(startDate, endDate);
    }

    @GetMapping("/{sprint}")
    public List<VacationDto> getAllVacationBySprint(@PathVariable(value = "sprint")String sprint){
        return vacationFacade.getAllvacationBySprint(sprint);
    }
}
