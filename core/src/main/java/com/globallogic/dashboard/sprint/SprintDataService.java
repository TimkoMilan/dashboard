package com.globallogic.dashboard.sprint;

import java.util.List;

public interface SprintDataService {

    List<SprintDataDto> getAllSprintDataBySprint(String sprint);

    List<SprintDataModel> getAllSprintDataByTeam(String teamName);

}
