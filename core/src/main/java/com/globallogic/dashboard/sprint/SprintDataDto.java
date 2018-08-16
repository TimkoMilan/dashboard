package com.globallogic.dashboard.sprint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;


public class SprintDataDto implements Serializable{

    private float storyPointsTaken;

    private float storyPointsClosed;

    private Long teamId;

    private String teamName;

    private Long sprintId;


    private Sprint sprint;
    @JsonIgnore
    private SprintDto sprintDto;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public SprintDto getSprintDto() {
        return sprintDto;
    }

    public void setSprintDto(SprintDto sprintDto) {
        this.sprintDto = sprintDto;
    }

    public void setStoryPointsTaken(Long storyPointsTaken) {
        this.storyPointsTaken = storyPointsTaken;
    }

    public void setStoryPointsClosed(Long storyPointsClosed) {
        this.storyPointsClosed = storyPointsClosed;
    }

    public Long getSprintId() {
        return sprintId;
    }

    public void setSprintId(Long sprintId) {
        this.sprintId = sprintId;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public float getStoryPointsTaken() {
        return storyPointsTaken;
    }

    public void setStoryPointsTaken(float storyPointsTaken) {
        this.storyPointsTaken = storyPointsTaken;
    }

    public float getStoryPointsClosed() {
        return storyPointsClosed;
    }

    public void setStoryPointsClosed(float storyPointsClosed) {
        this.storyPointsClosed = storyPointsClosed;
    }
}
