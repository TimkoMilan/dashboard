package com.globallogic.dashboard.service.impl;

import com.globallogic.dashboard.TeamUtil;
import com.globallogic.dashboard.event.VacationEventListener;
import com.globallogic.dashboard.model.Team;
import com.globallogic.dashboard.repository.TeamRepository;
import com.globallogic.dashboard.service.TeamService;
import com.globallogic.dashboard.to.TeamCreateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;
    private static final Logger log = LoggerFactory.getLogger(VacationEventListener.class);


    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team saveTeam(TeamCreateDto teamCreateDto) {
        Team team = TeamUtil.createTeamFromTeamCreateDto(teamCreateDto);
        return teamRepository.save(team);
    }
}
