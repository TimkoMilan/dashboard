package com.globallogic.dashboard.sprint;

 class SprintDataUtil {
    private SprintDataUtil(){

    }
     static SprintDataDto convertToDto (SprintData sprintData){
        SprintDataDto sprintDataDto = new SprintDataDto();

        sprintDataDto.setStoryPointsClosed(sprintData.getStoryPointsClosed());
        sprintDataDto.setStoryPointsTaken(sprintData.getStoryPointsTaken());
        sprintDataDto.setSprintId(sprintData.getSprint().getId());
        sprintDataDto.setTeamId(sprintData.getTeam().getId());

        return sprintDataDto;
    }
}
