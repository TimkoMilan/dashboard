package com.globallogic.dashboard.team;

import com.globallogic.dashboard.member.MemberUpdateDto;
import com.globallogic.dashboard.member.Memberservice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TeamFacadeImpl implements TeamFacade {
    private TeamService teamService;
    private Memberservice memberService;


    public TeamFacadeImpl(TeamService teamService, Memberservice memberService) {
        this.teamService = teamService;
        this.memberService = memberService;
    }

    @Override
    public Team saveTeam(TeamCreateDto teamCreateDto) {
        return null;
    }

    @Override
    public Team addNewMemberToTeam(MemberUpdateDto updateMemberDto) {
        return null;
    }



}
