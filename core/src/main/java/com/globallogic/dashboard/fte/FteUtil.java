package com.globallogic.dashboard.fte;

import com.globallogic.dashboard.team.Team;

public class FteUtil {

    private FteUtil(){}

    public static Fte fromCreate(FteCreateDto fteCreateDto, Team team){

        Fte fte = new Fte();
        fte.setTeam(team);
        fte.setFte(fteCreateDto.getFte());
        fte.setMonth(fteCreateDto.getMonth());
        fte.setYear(fteCreateDto.getYear());
        return fte;
    }

    public static FteResponseDto toFteResponseDto(Fte fte){

        return new FteResponseDto()
                .setFte(fte.getFte())
                .setMonth(fte.getMonth())
                .setTeamId(fte.getTeam().getId());
    }
}
