package com.globallogic.dashboard.sprint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("sprintdata")
public class SprintDataResource {

    private SprintDataFacade sprintDataFacade;

    public SprintDataResource(SprintDataFacade sprintDataFacade) {
        this.sprintDataFacade = sprintDataFacade;
    }

    @GetMapping("/loadData")
    public void loadData(){
         sprintDataFacade.loadSprintData();
    }

    @GetMapping
    public List<SprintDataDto> getAllSprintData(){
        return sprintDataFacade.getAllSprintData();
    }

    @GetMapping("sprint/{sprint}")
    public List<SprintDataDto> getAllSprintDataBySprint(@PathVariable(value = "sprint")@NotNull String sprint){
        return sprintDataFacade.getAllSprintDataBySprint(sprint);
    }
    @GetMapping("team/{teamName}")
    public List<SprintDataDto> getAllSprintDataByTeam(@PathVariable(value = "teamName")@NotNull String teamName){
        return sprintDataFacade.getAllSprintDataByTeam(teamName);
    }

}
