package com.globallogic.dashboard.sprint;

public class SprintUtil {
    private SprintUtil(){
    }
    public static SprintDto convertToDto(Sprint sprint){
        SprintDto sprintDto = new SprintDto();

        sprintDto.setName(sprint.getName());
        sprintDto.setStart(sprint.getStart());
        sprintDto.setEnd(sprint.getEnd());
        return sprintDto;
    }
}
