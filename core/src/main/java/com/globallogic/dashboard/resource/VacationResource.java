package com.globallogic.dashboard.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.globallogic.dashboard.VacationDto;
import com.globallogic.dashboard.event.VacationData;
import com.globallogic.dashboard.model.Vacation;
import com.globallogic.dashboard.service.VacationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("vacations")
public class VacationResource {

    private VacationService vacationService;

    public VacationResource(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping
    public List<VacationData> getAllVacations() {
        return vacationService.getAllVacations();
    }

    @GetMapping("/{name}")
    public List<Vacation> getVacationByMember(@PathVariable(value = "name")String name){
        return vacationService.getVacationByName(name);
    }
    @GetMapping("/byteam{teamid}")
    public List<Vacation>getVacationByTeam(@PathVariable(value = "teamid")Long teamid){
        return vacationService.getVacationByTeam(teamid);
    }

    @GetMapping("/date/{startDate}/{endDate}")
    public List<VacationDto>getVacationByMonth(@PathVariable(value = "startDate")@JsonFormat(pattern = "dd.mm.yyyy") Date startDate,
                                               @PathVariable(value = "endDate")@JsonFormat (pattern = "dd.mm.yyyy") Date endDate){

        return vacationService.getVacationbyMonth(startDate,endDate);
    }

}
