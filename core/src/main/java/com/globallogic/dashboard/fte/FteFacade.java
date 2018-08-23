package com.globallogic.dashboard.fte;

public interface FteFacade {

    FteResponseDto createFte(FteCreateDto fteCreateDto);
    Double findFteByTeamAndMonth(Byte month, Long teamId);

}
