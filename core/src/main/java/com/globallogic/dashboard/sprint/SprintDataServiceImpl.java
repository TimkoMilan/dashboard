package com.globallogic.dashboard.sprint;

import com.querydsl.core.BooleanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SprintDataServiceImpl implements SprintDataService{

    private static final Logger log = LoggerFactory.getLogger(SprintDataServiceImpl.class);
    private SprintDataRepository sprintDataRepository;


    public SprintDataServiceImpl(SprintDataRepository sprintDataRepository) {
        this.sprintDataRepository = sprintDataRepository;
    }

    public List<SprintDataDto> getAllSprintDataBySprint(String sprint) {
        List<SprintData> sprintData = sprintDataRepository.findAllBySprint_Name(sprint);
        return sprintData.stream().map(SprintDataUtil::convertToDto).collect(Collectors.toList());
    }

    public List<SprintDataDto> getAllSprintDataByTeam(String teamName) {
        List<SprintData> sprintData = sprintDataRepository.findAllByTeam_Name(teamName);
        return sprintData.stream().map(SprintDataUtil::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<SprintDataDto> findAllBySprint_NameAndTeamName(String sprintName, String teamName) {
        List<SprintData> sprintData = sprintDataRepository.findAllByTeam_NameAndSprint_Name(teamName,sprintName);
        return sprintData.stream().map(SprintDataUtil::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<SprintDataDto> getAllSprintData(SprintDataFilterDto sprintDataFilterDto) {
        if (sprintDataFilterDto == null){
            List<SprintData> sprintData = sprintDataRepository.findAll();
            return sprintData.stream().map(SprintDataUtil::convertToDto).collect(Collectors.toList());
        }
        else {
            BooleanBuilder booleanBuilder =  new BooleanBuilder();
            if (sprintDataFilterDto.getTeamId()!=null){
                booleanBuilder.and(QSprintData.sprintData.team.id.eq(sprintDataFilterDto.getTeamId()));
            }
            if (sprintDataFilterDto.getSprintId() != null){
                booleanBuilder.and(QSprintData.sprintData.sprint.id.eq(sprintDataFilterDto.getSprintId()));
            }
            return SprintDataUtil.convertToListdto(sprintDataRepository.findAll(booleanBuilder.getValue()));
        }
    }

    @Override
    public void save(SprintData sprintData) {
        sprintDataRepository.save(sprintData);
    }

}
