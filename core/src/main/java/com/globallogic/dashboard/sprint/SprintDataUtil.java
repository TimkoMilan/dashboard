package com.globallogic.dashboard.sprint;

import java.util.ArrayList;
import java.util.List;

class SprintDataUtil {
    private SprintDataUtil() {

    }

    static SprintDataDto convertToDto(SprintData sprintData) {
        SprintDataDto sprintDataDto = new SprintDataDto();

        sprintDataDto.setStoryPointsClosed(sprintData.getStoryPointsClosed());
        sprintDataDto.setStoryPointsTaken(sprintData.getStoryPointsTaken());
        sprintDataDto.setSprint(sprintData.getSprint());
        sprintDataDto.setTeamId(sprintData.getTeam().getId());
        sprintDataDto.setTeamName(sprintData.getTeam().getName());
        sprintDataDto.setSprintId(sprintData.getSprint().getId());
        return sprintDataDto;
    }

    static List<SprintDataDto> convertToListdto(Iterable<SprintData> sprintData) {
        List<SprintDataDto> sprintDataDtos = new ArrayList<>();
        for (SprintData sprintDatum : sprintData) {
            sprintDataDtos.add(convertToDto(sprintDatum));
        }
        return sprintDataDtos;
    }
    static List<SprintDataDto> convertToListDto(Iterable<SprintData> sprints){
        List <SprintDataDto> sprintDataDtos = new ArrayList<>();
        for (SprintData sprint : sprints) {
            sprintDataDtos.add(convertToDto(sprint));
        }
        return sprintDataDtos;
    }
}
