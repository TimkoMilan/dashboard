package com.globallogic.dashboard.team;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    Team saveTeam(TeamCreateDto teamCreateDto);

    Optional<Team> findById(Long id);

    List<TeamDto> findAll();

    TeamDto findByTeamId(Long teamId);

    Team findByTeamName(String name);

    List<TeamNameDto> getTeamName();

}
