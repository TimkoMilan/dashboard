package com.globallogic.dashboard.vacation;

import com.globallogic.dashboard.common.FilterToDtoAdapter;
import com.globallogic.dashboard.common.UrlFilterValueParser;
import org.apache.logging.log4j.util.Strings;
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

    @GetMapping("loadVacationData")//load data from excel
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

}
