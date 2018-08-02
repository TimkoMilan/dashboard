package com.globallogic.dashboard.sprint;

import java.util.List;

public interface SprintDataService {

    List<SprintDataDto> getAllSprintDataBySprint(String sprint);

    List<SprintDataDto> getAllSprintDataByTeam(String teamName);

    List<SprintDataDto> getSprintDataByTeamAndSprint(String teamName,String sprintName);

    List<SprintDataDto> getAllSprintData();

}
