package com.globallogic.dashboard.sprint;

import java.util.Date;
import java.util.List;

public interface SprintService {

    List<SprintDto> getSprintByDate(Date startDate,Date endDate);

}
