package com.globallogic.dashboard.sprint;

import java.util.Date;
import java.util.List;

public interface SprintService {

    List<SprintDto> getSprintByDate(Date startDate,Date endDate);
    Sprint findStartBySprintName(String sprint);
    Sprint findEndBySprintName(String sprint);
    Sprint findByName(String sprintName);
    void  save(Sprint sprint);

    List<SprintNameDto> getSprintsName();
}
