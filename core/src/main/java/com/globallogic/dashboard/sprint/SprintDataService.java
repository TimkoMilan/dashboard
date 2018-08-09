package com.globallogic.dashboard.sprint;

import java.util.List;

public interface SprintDataService {

    List<SprintDataDto> getAllSprintDataBySprint(String sprint);

    List<SprintDataDto> getAllSprintDataByTeam(String teamName);

    List<SprintDataDto> findAllBySprint_Name(String sprintName);

    List<SprintDataDto> getAllSprintData();

}
