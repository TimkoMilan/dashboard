package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.member.Member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class SprintData implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Long storyPointsTaken;

    private Long storyPointsClosed;

    @OneToOne
    private Member member;


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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }
}
