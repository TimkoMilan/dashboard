package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.event.SprintGeneratedData;
import com.globallogic.dashboard.loader.SprintLoader;
import com.globallogic.dashboard.team.TeamService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SprintDataFacadeImpl implements SprintDataFacade {

    private SprintLoader loadSprintData;
    private SprintDataRepository sprintDataRepository;
    private SprintDataService sprintDataService;
    private SprintService sprintService;
    private TeamService teamService;


    public SprintDataFacadeImpl(SprintLoader loadSprintData,SprintDataRepository sprintDataRepository, SprintDataService sprintDataService, SprintService sprintService, TeamService teamService) {
        this.loadSprintData = loadSprintData;
        this.sprintDataRepository = sprintDataRepository;
        this.sprintDataService = sprintDataService;
        this.sprintService = sprintService;
        this.teamService = teamService;
    }

    @Override
    public void loadSprintData() {
        Set<SprintGeneratedData> sprintGeneratedData = loadSprintData.loadSprintData();
        for (SprintGeneratedData sprintDatum : sprintGeneratedData) {
            String sprintName = sprintDatum.getName();
            List<SprintDataDto> sprints = sprintDataService.findAllBySprint_Name(sprintName);
            if (sprints.isEmpty()){
                SprintData sprintData = new SprintData();
                sprintData.setStoryPointsTaken(sprintDatum.getTaken());
                sprintData.setStoryPointsClosed(sprintDatum.getCompleted());
                String sprintsName = sprintDatum.getName();
                Sprint allBySprint_name = sprintService.findByName(sprintsName);

                if (allBySprint_name == null) {
                    Sprint sprint = new Sprint();
                    sprint.setName(sprintDatum.getName());
                    sprint.setStart(sprintDatum.getStart());
                    sprint.setEnd(sprintDatum.getEnd());
                    sprintService.save(sprint);
                    allBySprint_name = sprint;
                }
                sprintData.setSprint(allBySprint_name);
                sprintData.setTeam(teamService.finByTeamName(sprintDatum.getTeamName()));
                sprintDataRepository.save(sprintData);
            }
        }
    }
    @Override
    public List<SprintDataDto> getAllSprintData() {
        return sprintDataService.getAllSprintData();
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













