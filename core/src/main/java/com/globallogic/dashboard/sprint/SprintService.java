package com.globallogic.dashboard.sprint;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SprintService {

    List<SprintDto> getSprintByDate(Date startDate,Date endDate);
    Sprint findStartBySprintName(String sprint);
    Sprint findEndBySprintName(String sprint);
    Sprint findByName(String sprintName);
    void  save(Sprint sprint);
    Optional<Sprint> findById(Long sprintId);
    List<SprintDto> getSprintsByFilter(SprintFilterDto sprintFilterDto);

    List<SprintNameDto> getSprintsName();

    List<SprintDto> getAllSprints();
}
