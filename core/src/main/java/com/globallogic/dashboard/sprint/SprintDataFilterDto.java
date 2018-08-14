package com.globallogic.dashboard.sprint;

import java.io.Serializable;

public class SprintDataFilterDto implements Serializable {

    private float storyPointsTaken;

    private float storyPointsClosed;

    private String teamId;

    private String sprintId;


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

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }
}
