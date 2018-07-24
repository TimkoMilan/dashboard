package com.globallogic.dashboard.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("team")
public class TeamResource {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamFacade teamFacade;


    @PostMapping("/newteam")
    public Team createNewTeam(TeamCreateDto teamCreateDto){
        return teamService.saveTeam(teamCreateDto);
    }



}