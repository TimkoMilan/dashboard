package com.globallogic.dashboard.sprint;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SprintDataServiceImpl implements SprintDataService{

    private SprintDataRepository sprintDataRepository;
    private SprintRepository sprintRepository;


    public SprintDataServiceImpl(SprintDataRepository sprintDataRepository, SprintRepository sprintRepository) {
        this.sprintDataRepository = sprintDataRepository;
        this.sprintRepository = sprintRepository;
    }

    public List<SprintDataDto> getAllSprintDataBySprint(String sprint) {
        List<SprintData> sprintData = sprintDataRepository.findAllBySprint_Name(sprint);
        return sprintData.stream().map(SprintDataUtil::convertToDto).collect(Collectors.toList());
    }

    public List<SprintDataDto> getAllSprintDataByTeam(String teamName) {
        List<SprintData> sprintData = sprintDataRepository.findAllByTeam_Name(teamName);
        return sprintData.stream().map(SprintDataUtil::convertToDto).collect(Collectors.toList());
    }


}
