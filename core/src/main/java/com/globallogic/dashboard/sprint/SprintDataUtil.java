package com.globallogic.dashboard.sprint;

public class SprintDataUtil {
    private SprintDataUtil(){

    }
    public static SprintDataDto convertToDto (SprintData sprintData){
        SprintDataDto sprintDataDto = new SprintDataDto();
        sprintDataDto.setStoryPointsClosed(sprintData.getStoryPointsClosed());
        sprintDataDto.setStoryPointsTaken(sprintData.getStoryPointsTaken());
        sprintDataDto.setSprint(sprintData.getSprint());
        sprintDataDto.setTeamId(sprintData.getTeam().getId());
        return sprintDataDto;
    }
}
