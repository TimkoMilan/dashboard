package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.team.Team;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@SequenceGenerator(name="seqSprintData", initialValue=1000, allocationSize=1,sequenceName ="seqsprintdata" )
public class SprintData implements Serializable {

    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqSprintData")
    @Id
    private Long id;

    private Float storyPointsTaken;

    private Float storyPointsClosed;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Sprint sprint;


    public Team getTeam() {
        return team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
