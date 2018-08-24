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

    public Double findFteByTeamAndMonth(Byte monthByte, Long teamIdLong){
        Long teamId = teamIdLong;
        Byte month = monthByte;
        Optional<Team> optionalTeam = teamService.findById(teamId);
        Team team = optionalTeam.orElseThrow(() -> new ServiceException("Team not found"));

         if (fteService.findFteByTeamAndMonth(optionalTeam.get(),month)!= null){
             return fteService.findFteByTeamAndMonth(optionalTeam.get(),month);
         }
        else {
            return 0.0;
         }
    }


}
