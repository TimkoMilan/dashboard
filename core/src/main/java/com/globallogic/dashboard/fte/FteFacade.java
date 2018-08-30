package com.globallogic.dashboard.fte;

public interface FteFacade {

    FteResponseDto createFte(FteCreateDto fteCreateDto);
    Double findFteByTeamAndMonthAndYear(Byte month, Long teamId, Integer year);

}
