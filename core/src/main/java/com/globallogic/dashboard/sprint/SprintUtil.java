package com.globallogic.dashboard.sprint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SprintUtil {
    private SprintUtil() {
    }

     static SprintDto convertToDto(Sprint sprint) {
        SprintDto sprintDto = new SprintDto();

        sprintDto.setName(sprint.getName());
        sprintDto.setStart(sprint.getStart());
        sprintDto.setEnd(sprint.getEnd());
        sprintDto.setId(sprint.getId());
        Set<SprintDataDto> sprintDataDtos = new HashSet<>();

        for (SprintData sprintDatum : sprint.getSprintData()) {
            sprintDataDtos.add(SprintDataUtil.convertToDto(sprintDatum));
            sprintDto.setSprintDataDtos(sprintDataDtos);

        }

        return sprintDto;
    }


     static SprintNameDto converToSprintNameDto(Sprint sprint) {

        SprintNameDto sprintNameDto = new SprintNameDto();
        sprintNameDto.setSprintName(sprint.getName());
        sprintNameDto.setStart(sprint.getStart());
        sprintNameDto.setEnd(sprint.getEnd());
        return sprintNameDto;

    }

    static List<SprintDto> convertToListDto(Iterable<Sprint> sprints){
        List <SprintDto> sprintDtos = new ArrayList<>();
        for (Sprint sprint : sprints) {
            sprintDtos.add(convertToDto(sprint));
        }
        return sprintDtos;
    }

}
