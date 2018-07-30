package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.event.SprintData;
import com.globallogic.dashboard.loader.GoogleDataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class SprintDataService {

    private static final Logger log = LoggerFactory.getLogger(GoogleDataLoader.class);

    private SprintDataFacade sprintDataFacade;

    @Autowired
    private SprintDataRepository sprintDataRepository;

    public SprintDataService(SprintDataFacade sprintDataFacade) {
        this.sprintDataFacade = sprintDataFacade;
    }

    public Set<SprintData> getAllSprintData() throws GeneralSecurityException, IOException, ParseException {
        return sprintDataFacade.getAllSprintData();
    }

    public List<SprintDataDto> getAllSprintDataBySprint(String sprint) {
       List<SprintDataModel> sprintDataModels = (List<SprintDataModel>) sprintDataRepository.findAllBySprint_Name(sprint);
        return sprintDataModels.stream().map(SprintDataUtil::convertToDto).collect(Collectors.toList());
    }

    public List<SprintDataModel> getAllSprintDataByTeam(String teamName) {
        return sprintDataRepository.findAllByTeam_Name(teamName);
    }


}
