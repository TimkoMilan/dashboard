package com.globallogic.dashboard.sprint;

import java.util.List;

public interface SprintDataService {

    List<SprintDataDto> getAllSprintDataByTeam(String teamName);

    List<SprintDataDto> findAllBySprint_NameAndTeamName(String sprintName,String teamName);

    List<SprintDataDto> getAllSprintData(SprintDataFilterDto sprintDataFilterDto);

    void save(SprintData sprintData);

}
