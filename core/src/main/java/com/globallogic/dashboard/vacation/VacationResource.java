package com.globallogic.dashboard.vacation;

import com.globallogic.dashboard.common.FilterToDtoAdapter;
import com.globallogic.dashboard.common.UrlFilterValueParser;
import org.apache.logging.log4j.util.Strings;
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

    @GetMapping("loadVacationData")
    public void loadVacation(){
        vacationFacade.loadVacation();
    }

    @GetMapping
    public List<VacationDto> getVacations(@RequestParam(required = false) String filter) {
        VacationFilterDto vacationFilterDto = null;
        if (Strings.isNotBlank(filter)) {
            FilterToDtoAdapter<VacationFilterDto> vacationFilterDtoFilterToDtoAdapter = new FilterToDtoAdapter<>(filter, VacationFilterDto.class, new UrlFilterValueParser());
            vacationFilterDto = vacationFilterDtoFilterToDtoAdapter.getDto();

        }
        return vacationFacade.getVacations(vacationFilterDto);
    }

    @GetMapping("members/{name}")
    public List<VacationDto> getVacationByMember(@PathVariable(value = "name") String name) {
        return vacationFacade.getVacationByMemberName(name);
    }

    @GetMapping("/team/{teamId}")
    public List<VacationDto> getVacationByTeam(@PathVariable(value = "teamId") Long teamid) {
        return vacationFacade.getVacationByTeam(teamid);
    }

    @GetMapping("/date")
    public List<VacationDto> getVacationByMonth(@RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return vacationFacade.getVacationByMonth(startDate, endDate);
    }

    @GetMapping("/{sprint}")
    public List<VacationDto> getAllVacationBySprint(@PathVariable(value = "sprint") String sprint) {
        return vacationFacade.getAllVacationBySprint(sprint);
    }


}
