package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.team.Team;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class SprintDataModel implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String storyPointsTaken;

    private String storyPointsClosed;

    @ManyToOne
    private Team team;

    @OneToOne
    private Sprint sprint;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setTeam(Team team) {
        this.team = team;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public void setTeam(Set<Team> team) {
    }
}
