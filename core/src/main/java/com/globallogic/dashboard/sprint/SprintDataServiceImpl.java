package com.globallogic.dashboard.sprint;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SprintDataServiceImpl implements SprintDataService {

    private SprintDataRepository sprintDataRepository;


    public SprintDataServiceImpl(SprintDataRepository sprintDataRepository) {
        this.sprintDataRepository = sprintDataRepository;
    }

    public List<SprintDataDto> getAllSprintDataByTeam(String teamName) {
        List<SprintData> sprintData = sprintDataRepository.findAllByTeam_Name(teamName);
        return sprintData.stream().map(SprintDataUtil::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<SprintDataDto> findAllBySprint_NameAndTeamName(String sprintName, String teamName) {
        List<SprintData> sprintData = sprintDataRepository.findAllByTeam_NameAndSprint_Name(teamName, sprintName);
        return sprintData.stream().map(SprintDataUtil::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<SprintDataDto> getAllSprintData(SprintDataFilterDto sprintDataFilterDto) {
        if (sprintDataFilterDto == null) {
            List<SprintData> sprintData = sprintDataRepository.findAll();
            return sprintData.stream().map(SprintDataUtil::convertToDto).collect(Collectors.toList());
        } else {
            BooleanBuilder booleanBuilderTeamId = new BooleanBuilder();
            if (sprintDataFilterDto.getTeamId() != null) {
                for (String splitTeamId : sprintDataFilterDto.getTeamId().split(",")) {
                    booleanBuilderTeamId.or(QSprintData.sprintData.team.id.eq(Long.valueOf(splitTeamId)));
                }
            }

            BooleanBuilder booleanBuilderSprintId = new BooleanBuilder();
            if (sprintDataFilterDto.getSprintId() != null) {
                for (String splitSpringId : sprintDataFilterDto.getSprintId().split(",")) {
                    booleanBuilderSprintId.or(QSprintData.sprintData.sprint.id.eq(Long.valueOf(splitSpringId)));
                }
            }
            Predicate queryPredicate = booleanBuilderSprintId.and(booleanBuilderTeamId.getValue()).getValue();
            return SprintDataUtil.convertToListdto(sprintDataRepository.findAll(queryPredicate));
        }
    }

    @Override
    public void save(SprintData sprintData) {
        sprintDataRepository.save(sprintData);
    }

}
