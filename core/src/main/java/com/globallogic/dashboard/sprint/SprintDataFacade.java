package com.globallogic.dashboard.sprint;

import java.util.List;

public interface SprintDataFacade {

    void loadSprintData();
    List<SprintDataDto> getAllSprintData(SprintDataFilterDto sprintDataFilterDto) ;
    List<SprintDataDto> getAllSprintDataByTeam(String teamName);

}

