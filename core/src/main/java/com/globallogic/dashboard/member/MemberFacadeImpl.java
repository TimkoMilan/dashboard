package com.globallogic.dashboard.member;

import com.globallogic.dashboard.team.Team;
import com.globallogic.dashboard.team.TeamException;
import com.globallogic.dashboard.team.TeamService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberFacadeImpl implements MemberFacade {

    private MemberService memberservice;
    private TeamService teamService;

    public MemberFacadeImpl(MemberService memberservice, TeamService teamService) {
        this.memberservice = memberservice;
        this.teamService = teamService;
    }

    @Override
    public Member createMember(MemberCreateDto memberCreateDto) {
        return memberservice.createMember(memberCreateDto);
    }

    @Override
    public List<MemberDto> getAllMembers() {
        List<Member> members = memberservice.getAllMembers();
        return members.stream().map(MemberUtil::createDto).collect(Collectors.toList());
    }

    @Override
    public void assignToTeam(Long memberId, Long teamId) {
        Optional<Member> memberToBeUpdated = memberservice.findById(memberId);
        if (memberToBeUpdated.isPresent()) {
            Member member = memberToBeUpdated.get();
                Optional<Team> team = teamService.findById(teamId);
                if (team.isPresent()) {
                    member.setTeam(team.get());
                }else {
                    throw new TeamException("Team with id: {0} not found",teamId.toString());
                }
        } else {
            throw new MemberException("Member with id: {0} not found", memberId.toString());
        }
    }
}
