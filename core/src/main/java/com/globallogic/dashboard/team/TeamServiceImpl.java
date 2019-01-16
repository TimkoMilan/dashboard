package com.globallogic.dashboard.team;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (teamRepository.findTeamByNameIgnoreCase(teamName)==null){
            return teamRepository.save(team);
        }
        return teamRepository.findTeamByNameIgnoreCase(teamName);
    }
    @Override
    public List<TeamDto> findAll() {
        List<Team>teams = teamRepository.findAllByOrderByNameAsc();
        return teams.stream().map(TeamUtil::convertToDto).collect(Collectors.toList());
    }

    @Override
    public TeamDto findByTeamId(Long teamId) {
        Team team = teamRepository.findTeamById(teamId);
        return TeamUtil.convertToDto(team);

    }

    @Override
    public Team findByTeamName(String name) {
        return teamRepository.findTeamByNameIgnoreCase(name);
    }

    @Override
    public List<TeamNameDto> getTeamName() {
        List<Team>teams = teamRepository.findAllByOrderByNameAsc();
        return teams.stream().map(TeamUtil::convertToTeamNameDto).collect(Collectors.toList());

    }

    @Override
    public String teamTest() {
        return "teamTest";
    }

    @Override
    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }


}
