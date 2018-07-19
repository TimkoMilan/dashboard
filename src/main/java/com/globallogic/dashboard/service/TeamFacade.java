package com.globallogic.dashboard.service;

import com.globallogic.dashboard.model.Team;
import com.globallogic.dashboard.to.TeamCreateDto;
import com.globallogic.dashboard.to.UpdateMemberDto;

public interface TeamFacade {

    Team saveTeam(TeamCreateDto teamCreateDto);

    Team addNewMemberToTeam(UpdateMemberDto updateMemberDto);

}
