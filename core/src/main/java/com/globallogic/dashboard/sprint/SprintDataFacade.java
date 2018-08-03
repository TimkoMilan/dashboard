package com.globallogic.dashboard.sprint;

import java.util.List;

public interface SprintDataFacade {

    void loadSprintData();
    List<SprintDataDto> getAllSprintData() ;
    List<SprintDataDto> getAllSprintDataBySprint(String sprint);
    List<SprintDataDto> getAllSprintDataByTeam(String teamName);

}

