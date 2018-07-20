package com.globallogic.dashboard.service;

import com.globallogic.dashboard.to.UpdateMemberDto;
import com.globallogic.dashboard.model.Team;
import com.globallogic.dashboard.to.TeamCreateDto;

public interface TeamFacade {

    Team saveTeam(TeamCreateDto teamCreateDto);

    Team addNewMemberToTeam(UpdateMemberDto updateMemberDto);

}
