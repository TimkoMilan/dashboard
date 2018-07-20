package com.globallogic.dashboard.service.impl;

import com.globallogic.dashboard.model.Member;
import com.globallogic.dashboard.model.Team;
import com.globallogic.dashboard.service.Memberservice;
import com.globallogic.dashboard.service.TeamFacade;
import com.globallogic.dashboard.service.TeamService;
import com.globallogic.dashboard.to.TeamCreateDto;
import com.globallogic.dashboard.to.UpdateMemberDto;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
    public Team addNewMemberToTeam(UpdateMemberDto updateMemberDto) {
        Optional<Member> memberToBeUpdated = memberService.findById(updateMemberDto.getId());
        if (memberToBeUpdated.isPresent()) {
            String memberName = updateMemberDto.getMemberName();
            Member member = memberToBeUpdated.get();
            if (!Strings.isBlank(memberName)) {
                member.setName(memberName);
            }
            if (updateMemberDto.getIdTeam() != null) {
                Optional<Team> team = teamService.findById(updateMemberDto.getIdTeam());
                if(team.isPresent()){
                    member.setTeam(team.get());
                }
            }
        }
        return null;
    }


}
