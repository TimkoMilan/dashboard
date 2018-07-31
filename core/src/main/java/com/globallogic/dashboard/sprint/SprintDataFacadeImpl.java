package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.event.SprintData;
import com.globallogic.dashboard.loader.SprintLoader;
import com.globallogic.dashboard.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SprintDataFacadeImpl implements SprintDataFacade {

    private SprintLoader loadSprintData;
    private SprintRepository sprintRepository;
    private SprintDataRepository sprintDataRepository;
    private TeamRepository teamRepository;

    public SprintDataFacadeImpl(SprintLoader loadSprintData, SprintRepository sprintRepository, SprintDataRepository sprintDataRepository, TeamRepository teamRepository) {
        this.loadSprintData = loadSprintData;
        this.sprintRepository = sprintRepository;
        this.sprintDataRepository = sprintDataRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public Set<SprintData> getAllSprintData() throws GeneralSecurityException, IOException, ParseException {
        Set<SprintData> sprintData = loadSprintData.loadSprintData();
        for (SprintData sprintDatum : sprintData) {

            SprintDataModel sprintDataModel = new SprintDataModel();

            sprintDataModel.setStoryPointsTaken(sprintDatum.getTaken());
            sprintDataModel.setStoryPointsClosed(sprintDatum.getCompleted());

            Sprint allBySprint_name = sprintRepository.findByName(sprintDatum.getName());
            if (allBySprint_name == null) {
                Sprint sprint = new Sprint();
                sprint.setName(sprintDatum.getName());
                sprint.setStart(sprintDatum.getStart());
                sprint.setEnd(sprintDatum.getEnd());
                sprintRepository.save(sprint);
                allBySprint_name = sprint;
            }
            sprintDataModel.setSprint(allBySprint_name);
            sprintDataModel.setTeam(teamRepository.findTeamByName(sprintDatum.getTeamName()));
            sprintDataRepository.save(sprintDataModel);
        }
        return sprintData;
    }
}













