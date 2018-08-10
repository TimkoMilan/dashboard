package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.common.FilterToDtoAdapter;
import com.globallogic.dashboard.common.UrlFilterValueParser;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("sprintdata")
public class SprintDataResource {

    private SprintDataFacade sprintDataFacade;
    public SprintDataResource(SprintDataFacade sprintDataFacade) {
        this.sprintDataFacade = sprintDataFacade;
    }

    @GetMapping
    public List<SprintDataDto> getAllSprintData(@RequestParam(required = false)String filter){
        SprintDataFilterDto sprintDataFilterDto = null;
        if (Strings.isNotBlank(filter)){
            FilterToDtoAdapter<SprintDataFilterDto> sprintDataFilterDtoFilterToDtoAdapter = new FilterToDtoAdapter<>(filter,SprintDataFilterDto.class,new UrlFilterValueParser());
            sprintDataFilterDto = sprintDataFilterDtoFilterToDtoAdapter.getDto();
        }
        return sprintDataFacade.getAllSprintData(sprintDataFilterDto);
    }

    @GetMapping("/loadData")
    public void loadData(){
        sprintDataFacade.loadSprintData();
    }

    @GetMapping("team/{teamName}")
    public List<SprintDataDto> getAllSprintDataByTeam(@PathVariable(value = "teamName")@NotNull String teamName){
        return sprintDataFacade.getAllSprintDataByTeam(teamName);
    }

}
