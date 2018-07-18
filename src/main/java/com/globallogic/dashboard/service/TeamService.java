package com.globallogic.dashboard.service;

import com.globallogic.dashboard.model.Team;
import com.globallogic.dashboard.to.TeamCreateDto;

public interface TeamService {

    Team saveTeam(TeamCreateDto teamCreateDto);
}
