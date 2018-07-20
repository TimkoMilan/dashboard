package com.globallogic.dashboard.service.impl;

import com.globallogic.dashboard.TeamUtil;
import com.globallogic.dashboard.model.Team;
import com.globallogic.dashboard.repository.TeamRepository;
import com.globallogic.dashboard.service.TeamService;
import com.globallogic.dashboard.to.TeamCreateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;


    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team saveTeam(TeamCreateDto teamCreateDto) {
        Team team = TeamUtil.createTeamFromTeamCreateDto(teamCreateDto);
        String teamName = teamCreateDto.getName();
        team.setFocus(teamCreateDto.getFocus());
        if (teamRepository.findTeamByProjectName(teamName)==null){
            return teamRepository.save(team);
        }
        return teamRepository.findTeamByProjectName(teamName);
    }

    @Override
    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }
}
