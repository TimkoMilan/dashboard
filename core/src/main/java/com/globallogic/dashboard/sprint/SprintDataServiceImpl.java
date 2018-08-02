package com.globallogic.dashboard.sprint;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SprintDataServiceImpl implements SprintDataService{

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
    public List<SprintDataDto> getSprintDataByTeamAndSprint(String teamName, String sprintName) {
        List <SprintData> sprints = sprintDataRepository.findAllByTeam_NameAndSprint_Name(teamName,sprintName);
        return sprints.stream().map(SprintDataUtil::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<SprintDataDto> getAllSprintData() {
       List<SprintData> sprintData = sprintDataRepository.findAll();
        return sprintData.stream().map(SprintDataUtil::convertToDto).collect(Collectors.toList());
    }


}
