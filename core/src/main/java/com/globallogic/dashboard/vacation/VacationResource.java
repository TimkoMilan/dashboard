package com.globallogic.dashboard.vacation;

import com.globallogic.dashboard.common.FilterToDtoAdapter;
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

    @GetMapping
    public List<VacationDto> getAllVacations(@RequestParam(required = false) String filter) {

        //todo filter to object adapter
        //FilterToDtoAdapter adapter= new FilterToDtoAdapter(filter, DtoFilteringClass.class) ->filter is the string form request, DtoFilteringClass is the dto we want the filter transform to in our case its VacationFilterDto


        if (Strings.isNotBlank(filter)) {
            FilterToDtoAdapter<VacationFilterDto> vacationFilterDtoFilterToDtoAdapter = new FilterToDtoAdapter<>(filter, VacationFilterDto.class);
            VacationFilterDto dto = vacationFilterDtoFilterToDtoAdapter.getDto();
        }
      return null;
//        return vacationFacade.getVacations(vacationFilterDto);
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
