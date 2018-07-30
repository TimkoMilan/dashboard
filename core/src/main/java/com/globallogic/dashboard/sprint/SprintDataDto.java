package com.globallogic.dashboard.sprint;

import java.io.Serializable;


public class SprintDataDto implements Serializable {

    private String storyPointsTaken;

    private String storyPointsClosed;

    private String team;

    private Sprint sprint;


    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getStoryPointsTaken() {
        return storyPointsTaken;
    }

    public void setStoryPointsTaken(String storyPointsTaken) {
        this.storyPointsTaken = storyPointsTaken;
    }

    public String getStoryPointsClosed() {
        return storyPointsClosed;
    }

    public void setStoryPointsClosed(String storyPointsClosed) {
        this.storyPointsClosed = storyPointsClosed;
    }
}
