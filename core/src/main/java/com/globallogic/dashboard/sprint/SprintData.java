package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.team.Team;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class SprintData implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Long storyPointsTaken;

    private Long storyPointsClosed;

    @OneToMany
    private Set<Team> team;


    @ManyToOne//todo joincolumn
    private Sprint sprint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Team> getTeam() {
        return team;
    }

    public void setTeam(Set<Team> team) {
        this.team = team;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }
}
