package com.globallogic.dashboard.service;

import com.globallogic.dashboard.model.Team;
import com.globallogic.dashboard.to.TeamCreateDto;

import java.util.Optional;

public interface TeamService {

    Team saveTeam(TeamCreateDto teamCreateDto);

    Optional<Team> findById(Long id);

}
