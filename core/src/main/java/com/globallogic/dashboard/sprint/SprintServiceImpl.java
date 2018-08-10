package com.globallogic.dashboard.sprint;

import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    @Override
    public Optional<Sprint> findById(Long sprintId) {
        Optional<Sprint> sprint = sprintRepository.findById(sprintId);
        return sprint;
    }

    @Override
    public List<SprintNameDto> getSprintsName() {
        List<Sprint>sprints=sprintRepository.findAll();
        return sprints.stream().map(SprintUtil::converToSprintNameDto).collect(Collectors.toList());
    }

    @Override
    public List<SprintDto> getAllSprints() {
        List<Sprint>sprints=sprintRepository.findAll();
        return sprints.stream().map(SprintUtil::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<SprintDto> getSprintsByFilter(SprintFilterDto sprintFilterDto) {
        if (sprintFilterDto == null){
            List <Sprint> sprints =sprintRepository.findAll();
            return sprints.stream().map(SprintUtil::convertToDto).collect(Collectors.toList());

        }
        else {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            if (sprintFilterDto.getSprintId() !=null){
//                booleanBuilder.and(QSprint.sprint.sprintData.id.eq(sprintFilterDto.getSprintId()));
            }
            if (sprintFilterDto.getTeamId() != null){
//                booleanBuilder.and(QSprint.sprint.sprintData.team.id.eq(sprintFilterDto.getTeamId()));
            }
            return SprintUtil.convertToListDto(sprintRepository.findAll(booleanBuilder.getValue()));
        }
    }
}
