package com.globallogic.dashboard.sprint;

import java.util.ArrayList;
import java.util.List;

public final class SprintUtil {
    private SprintUtil() {
    }

     static SprintDto convertToDto(Sprint sprint) {
        SprintDto sprintDto = new SprintDto();

        sprintDto.setName(sprint.getName());
        sprintDto.setStart(sprint.getStart());
        sprintDto.setEnd(sprint.getEnd());
        sprintDto.setId(sprint.getId());


        return sprintDto;
    }


    static List<SprintDto> convertToListDto(Iterable<Sprint> sprints){
        List <SprintDto> sprintDtos = new ArrayList<>();
        for (Sprint sprint : sprints) {
            sprintDtos.add(convertToDto(sprint));
        }
        return sprintDtos;
    }

}
