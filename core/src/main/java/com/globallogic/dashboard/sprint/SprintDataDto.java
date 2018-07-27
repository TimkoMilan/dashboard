package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.team.Team;

import java.io.Serializable;


public class SprintDataDto implements Serializable {

    private Long storyPointsTaken;

    private Long storyPointsClosed;

    private SprintDto sprintDto;

    private Team team;

    private Sprint sprint;


    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getStoryPointsTaken() {
        return storyPointsTaken;
    }

    public void setStoryPointsTaken(Long storyPointsTaken) {
        this.storyPointsTaken = storyPointsTaken;
    }

    public Long getStoryPointsClosed() {
        return storyPointsClosed;
    }

    public void setStoryPointsClosed(Long storyPointsClosed) {
        this.storyPointsClosed = storyPointsClosed;
    }

    public SprintDto getSprintDto() {
        return sprintDto;
    }

    public void setSprintDto(SprintDto sprintDto) {
        this.sprintDto = sprintDto;
    }


}
