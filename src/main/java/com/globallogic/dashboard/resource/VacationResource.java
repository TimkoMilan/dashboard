package com.globallogic.dashboard.resource;

import com.globallogic.dashboard.VacationDto;
import com.globallogic.dashboard.model.Vacation;
import com.globallogic.dashboard.service.VacationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("vacations")
public class VacationResource {

    private VacationService vacationService;

    public VacationResource(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping
    public List<VacationDto> getAllVacations() {
        return vacationService.getAllVacations();
    }

    @GetMapping("/{name}")
    public List<Vacation> getVacationByMember(@PathVariable(value = "name")String name){
        return vacationService.getVacationByName(name);
    }
}
