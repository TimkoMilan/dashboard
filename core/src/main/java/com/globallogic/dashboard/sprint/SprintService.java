package com.globallogic.dashboard.sprint;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SprintService {

    List<SprintDto> getSprintByDate(Date startDate,Date endDate);
    Sprint findByName(String sprintName);
    void  save(Sprint sprint);
    SprintDto findById(Long sprintId);
    List<SprintDto> getSprintsByFilter(SprintFilterDto sprintFilterDto);
    List<SprintDto> getSprintsName();

    List<SprintDto> getAllSprints();
}
