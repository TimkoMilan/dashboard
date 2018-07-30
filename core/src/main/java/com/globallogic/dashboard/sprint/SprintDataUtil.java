package com.globallogic.dashboard.sprint;

public class SprintDataUtil {
    private SprintDataUtil(){

    }
    public static SprintDataDto convertToDto (SprintDataModel sprintDataModel){
        SprintDataDto sprintDataDto = new SprintDataDto();
        sprintDataDto.setStoryPointsClosed(sprintDataModel.getStoryPointsClosed());
        sprintDataDto.setStoryPointsTaken(sprintDataModel.getStoryPointsTaken());
        sprintDataDto.setSprint(sprintDataModel.getSprint());

        return sprintDataDto;
    }
}
