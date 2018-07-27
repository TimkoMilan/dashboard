package com.globallogic.dashboard.team;

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
        if (teamRepository.findTeamByName(teamName)==null){
            return teamRepository.save(team);
        }
        return teamRepository.findTeamByName(teamName);
    }

    @Override
    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }
}
