package com.globallogic.dashboard.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("team")
public class TeamResource {

    @Autowired
    private TeamService teamService;

    @GetMapping()
    public List<TeamDto> allTeam() {
        return teamService.findAll();
    }

    @PostMapping()
    public Team createNewTeam(@RequestBody TeamCreateDto teamCreateDto) {
        return teamService.saveTeam(teamCreateDto);
    }

    @GetMapping("/{teamId}")
    public TeamDto findTeamById(@PathVariable(value = "teamId") Long teamId) {
        return teamService.findByTeamId(teamId);
    }

    @GetMapping("/names")
    public List<TeamNameDto> getOnlyTeamName() {
        return teamService.getTeamName();
    }


    @GetMapping("/test")
    public String teamTest() {
        return teamService.teamTest();
    }

}