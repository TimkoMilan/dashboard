package com.globallogic.dashboard.fte;

import com.globallogic.dashboard.common.ServiceException;
import com.globallogic.dashboard.team.Team;
import com.globallogic.dashboard.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FteFacadeImpl implements FteFacade {

    @Autowired
    private TeamService teamService;

    @Autowired
    private FteService fteService;


    @Override
    public FteResponseDto createFte(FteCreateDto fteCreateDto) {
        Optional<Team> optionalTeam = teamService.findById(fteCreateDto.getTeamId());
        Team team = optionalTeam.orElseThrow(() -> new ServiceException("Team not found"));
        Fte fte = FteUtil.fromCreate(fteCreateDto, team);
        Fte returnedFte = fteService.createFte(fte);
        return FteUtil.toFteResponseDto(returnedFte);
    }


}
