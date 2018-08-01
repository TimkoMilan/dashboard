package com.globallogic.dashboard.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("team")
public class TeamResource {

    @Autowired
    private TeamService teamService;
    @GetMapping("all")
    public List<TeamDto> allTeam(){
        return teamService.findAll();
    }

    @PostMapping("/newteam")
    public Team createNewTeam(TeamCreateDto teamCreateDto){
        return teamService.saveTeam(teamCreateDto);
    }

    @GetMapping("/byId/{teamId}")
    public TeamDto findTeamById(@PathVariable(value = "teamId")Long teamId){
        return teamService.findByTeamId(teamId);
    }

}