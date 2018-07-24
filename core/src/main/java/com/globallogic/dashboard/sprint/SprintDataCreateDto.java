package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.sprint.Sprint;
import com.globallogic.dashboard.member.Member;


public class SprintDataCreateDto {

    private Long storyPointsTaken;

    private Long storyPointsClosed;

    private Member member;

    private Sprint sprint;



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
