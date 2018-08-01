package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.event.SprintGeneratedData;
import com.globallogic.dashboard.loader.SprintLoader;
import com.globallogic.dashboard.team.TeamRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SprintDataFacadeImpl implements SprintDataFacade {

    private SprintLoader loadSprintData;
    private SprintRepository sprintRepository;
    private SprintDataRepository sprintDataRepository;
    private TeamRepository teamRepository;
    private SprintDataService sprintDataService;

    public SprintDataFacadeImpl(SprintLoader loadSprintData, SprintRepository sprintRepository, SprintDataRepository sprintDataRepository, TeamRepository teamRepository, SprintDataService sprintDataService) {
        this.loadSprintData = loadSprintData;
        this.sprintRepository = sprintRepository;
        this.sprintDataRepository = sprintDataRepository;
        this.teamRepository = teamRepository;
        this.sprintDataService = sprintDataService;
    }

    @Override
    public Set<SprintGeneratedData> getAllSprintData() {
        Set<SprintGeneratedData> sprintGeneratedData = loadSprintData.loadSprintData();
        for (SprintGeneratedData sprintDatum : sprintGeneratedData) {

            SprintData sprintData = new SprintData();

            sprintData.setStoryPointsTaken(sprintDatum.getTaken());
            sprintData.setStoryPointsClosed(sprintDatum.getCompleted());

            Sprint allBySprint_name = sprintRepository.findByName(sprintDatum.getName());
            if (allBySprint_name == null) {
                Sprint sprint = new Sprint();
                sprint.setName(sprintDatum.getName());
                sprint.setStart(sprintDatum.getStart());
                sprint.setEnd(sprintDatum.getEnd());
                sprintRepository.save(sprint);
                allBySprint_name = sprint;
            }
            sprintData.setSprint(allBySprint_name);
            sprintData.setTeam(teamRepository.findTeamByName(sprintDatum.getTeamName()));
            sprintDataRepository.save(sprintData);
        }
        return sprintGeneratedData;
    }

    @Override
    public List<SprintDataDto> getAllSprintDataBySprint(String sprint) {
        return sprintDataService.getAllSprintDataBySprint(sprint);
    }

    @Override
    public List<SprintDataDto> getAllSprintDataByTeam(String teamName) {
        return sprintDataService.getAllSprintDataByTeam(teamName);
    }

}













