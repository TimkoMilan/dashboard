package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.event.SprintGeneratedData;

import java.util.List;
import java.util.Set;

public interface SprintDataFacade {

    void loadSprintData();
    Set<SprintGeneratedData> getAllSprintData() ;
    List<SprintDataDto> getAllSprintDataBySprint(String sprint);
    List<SprintDataDto> getAllSprintDataByTeam(String teamName);

}

