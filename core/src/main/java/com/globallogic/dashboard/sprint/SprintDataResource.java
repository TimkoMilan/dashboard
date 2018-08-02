package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.event.SprintGeneratedData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("sprintdata")
public class SprintDataResource {

    private SprintDataFacade sprintDataFacade;

    public SprintDataResource(SprintDataFacade sprintDataFacade) {
        this.sprintDataFacade = sprintDataFacade;
    }

    @GetMapping
    public Set<SprintGeneratedData> getAllSprintData(){
        return sprintDataFacade.getAllSprintData();
    }

    @GetMapping("sprint/{sprint}")
    public List<SprintDataDto> getAllSprintDataBySprint(@PathVariable(value = "sprint")String sprint){
        return sprintDataFacade.getAllSprintDataBySprint(sprint);
    }
    @GetMapping("team/{teamName}")
    public List<SprintDataDto> getAllSprintDataByTeam(@PathVariable(value = "teamName")String teamName){
        return sprintDataFacade.getAllSprintDataByTeam(teamName);
    }


}
