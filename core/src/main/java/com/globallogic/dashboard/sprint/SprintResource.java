package com.globallogic.dashboard.sprint;

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
    public List<SprintDto> getSprintByFilter(@RequestParam(required = false)String teamId,@RequestParam(required = false)String sprintId){
        SprintFilterDto sprintFilterDto = new SprintFilterDto();
        sprintFilterDto.setSprintId(sprintId);
        sprintFilterDto.setTeamId(teamId);
        return sprintService.getSprintsByFilter(sprintFilterDto);
    }


    @GetMapping("/names/")
    public List<SprintDto> getSprintNameAndDate(){
        return sprintService.getSprintsName();
    }
}
