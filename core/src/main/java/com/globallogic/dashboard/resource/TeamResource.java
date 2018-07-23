package com.globallogic.dashboard.resource;

import com.globallogic.dashboard.model.Team;
import com.globallogic.dashboard.service.TeamFacade;
import com.globallogic.dashboard.service.TeamService;
import com.globallogic.dashboard.to.TeamCreateDto;
import com.globallogic.dashboard.to.UpdateMemberDto;
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

    @PostMapping
    public Team insertMemberToTeam(UpdateMemberDto updateMemberDto){
        return teamFacade.addNewMemberToTeam(updateMemberDto);
    }
}
