package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.common.FilterToDtoAdapter;
import com.globallogic.dashboard.common.UrlFilterValueParser;
import org.apache.logging.log4j.util.Strings;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("sprint")
public class SprintResource {

    private SprintService sprintService;

    public SprintResource(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping
    public List<SprintDto> getSprintByFilter(@RequestParam(required = false)String filter){
        SprintFilterDto sprintFilterDto = null;
        if (Strings.isNotBlank(filter)){
            FilterToDtoAdapter<SprintFilterDto> sprintFilterDtoFilterToDtoAdapter = new FilterToDtoAdapter<>(filter,SprintFilterDto.class,new UrlFilterValueParser());
            sprintFilterDto = sprintFilterDtoFilterToDtoAdapter.getDto();
        }
        return sprintService.getSprintsByFilter(sprintFilterDto);
    }


    @GetMapping("date")
    public List<SprintDto> getSprintsByMonth(@RequestParam(value = "startDate")@DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                             @RequestParam(value = "endDate")@DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate){
        return sprintService.getSprintByDate(startDate,endDate);
    }
    @GetMapping("/names/")
    public List<SprintNameDto> getSprintNameAndDate(){
        return sprintService.getSprintsName();
    }
}
