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

    @Override
    public Sprint findStartBySprintName(String sprint) {
        return sprintRepository.findByName(sprint);
    }

    @Override
    public Sprint findEndBySprintName(String sprint) {
        return sprintRepository.findByName(sprint);
    }

    @Override
    public Sprint findByName(String sprintName) {
        return null;
    }

    @Override
    public void save(Sprint sprint) {
        sprintRepository.save(sprint);
    }

}
