package com.globallogic.dashboard.sprint;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SprintServiceImpl implements SprintService {

    private SprintRepository sprintRepository;

    public SprintServiceImpl(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    @Override
    public List<SprintDto> getSprintByDate(Date startDate, Date endDate) {
        List<Sprint> sprints = sprintRepository.findSprintsByStartOrEndIsBetween(startDate, endDate);
            return sprints.stream().map(SprintUtil::convertToDto).collect(Collectors.toList());
    }
}
