package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.event.SprintData;
import com.globallogic.dashboard.loader.SprintLoader;
import com.globallogic.dashboard.team.Team;
import com.globallogic.dashboard.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SprintDataFacadeImpl implements SprintDataFacade {

    @Autowired
    private SprintLoader loadSprintData;
    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private SprintDataRepository sprintDataRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<SprintData> getAllSprintData() throws GeneralSecurityException, IOException {
        List<SprintData> sprintData = loadSprintData.loadSprintData();
        for (SprintData sprintData1 : sprintData) {
            Sprint sprint = new Sprint();
            sprint.setName(sprintData1.getName());

            SprintDataModel sprintDataModel = new SprintDataModel();
            sprintDataModel.setStoryPointsTaken(sprintData1.getTaken());
            sprintDataModel.setStoryPointsClosed(sprintData1.getCompleted());
            sprintDataModel.setSprint(sprint);
            sprintDataModel.setTeam(teamRepository.findTeamByName(sprintData1.getTeam()));

            sprintRepository.save(sprint);
            sprintDataRepository.save(sprintDataModel);
        }
        return sprintData;
    }
}













