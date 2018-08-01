package com.globallogic.dashboard.sprint;

import java.io.Serializable;


public class SprintDataDto implements Serializable {

    private float storyPointsTaken;

    private float storyPointsClosed;

    private Long teamId;

    private Sprint sprint;


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
