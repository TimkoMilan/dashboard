package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.event.SprintData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("sprintdata")
public class SprintDataResource {

    private SprintDataService sprintDataService;

    public SprintDataResource(SprintDataService sprintDataService) {
        this.sprintDataService = sprintDataService;
    }

    @GetMapping
    public List<SprintData> getAllSprintData() throws GeneralSecurityException, IOException {
        return sprintDataService.getAllSprintData();
    }
    @GetMapping("sprint/{sprint}")
    public List<SprintDataModel> getAllSprintDataBySprint(@PathVariable(value = "sprint")String sprint){
        return sprintDataService.getAllSprintDataBySprint(sprint);
    }
    @GetMapping("team/{teamName}")
    public List<SprintDataModel> getAllSprintDataByTeam(@PathVariable(value = "teamName")String teamName){
        return sprintDataService.getAllSprintDataByTeam(teamName);
    }




}
