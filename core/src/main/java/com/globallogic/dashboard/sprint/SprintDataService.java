package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.event.SprintData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
@Transactional
public class SprintDataService {

    private SprintDataFacade sprintDataFacade;

    @Autowired
    private SprintDataRepository sprintDataRepository;

    public SprintDataService(SprintDataFacade sprintDataFacade) {
        this.sprintDataFacade = sprintDataFacade;
    }

    public List<SprintData> getAllSprintData() throws GeneralSecurityException, IOException {
        return sprintDataFacade.getAllSprintData();
    }
    //TODO dorobit dto objekty
    public List<SprintDataModel> getAllSprintDataBySprint(String sprint) {
        return sprintDataRepository.findAllBySprint_Name(sprint);
    }

    public List<SprintDataModel> getAllSprintDataByTeam(String teamName) {
        return sprintDataRepository.findAllByTeam_Name(teamName);
    }
}
