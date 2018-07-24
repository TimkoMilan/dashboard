package com.globallogic.dashboard.team;

import java.util.Optional;

public interface TeamService {

    Team saveTeam(TeamCreateDto teamCreateDto);

    Optional<Team> findById(Long id);

}
