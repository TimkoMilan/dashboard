package com.globallogic.dashboard.sprint;

import java.io.Serializable;


public class SprintDataDto implements Serializable{

    private float storyPointsTaken;

    private float storyPointsClosed;

    private Long teamId;

    private Long sprintId;

    private Sprint sprint;



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
