package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.common.FilterToDtoAdapter;
import com.globallogic.dashboard.common.UrlFilterValueParser;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/names/")
    public List<SprintDto> getSprintNameAndDate(){
        return sprintService.getSprintsName();
    }
}
