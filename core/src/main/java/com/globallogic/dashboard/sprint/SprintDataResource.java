package com.globallogic.dashboard.sprint;

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
    public List<SprintDataDto> getAllSprintData(@RequestParam(required = false)String teamId,@RequestParam(required = false)String sprintId){
        SprintDataFilterDto sprintDataFilterDto = new SprintDataFilterDto();
        sprintDataFilterDto.setSprintId(sprintId);
        sprintDataFilterDto.setTeamId(teamId);
        return sprintDataFacade.getAllSprintData(sprintDataFilterDto);
    }

    @GetMapping("/loadData")//loading data from excel
    public void loadData(){
        sprintDataFacade.loadSprintData();
    }

    @GetMapping("teams/{teamName}")
    public List<SprintDataDto> getAllSprintDataByTeam(@PathVariable(value = "teamName")@NotNull String teamName){
        return sprintDataFacade.getAllSprintDataByTeam(teamName);
    }

}
