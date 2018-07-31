package com.globallogic.dashboard.sprint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        List<SprintDataModel> sprintDataModels = sprintDataRepository.findAllBySprint_Name(sprint);
        return sprintDataModels.stream().map(SprintDataUtil::convertToDto).collect(Collectors.toList());
    }

    public List<SprintDataModel> getAllSprintDataByTeam(String teamName) {
        return sprintDataRepository.findAllByTeam_Name(teamName);
    }

}
