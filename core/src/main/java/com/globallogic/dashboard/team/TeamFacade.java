package com.globallogic.dashboard.team;

import com.globallogic.dashboard.member.MemberUpdateDto;

public interface TeamFacade {

    Team saveTeam(TeamCreateDto teamCreateDto);

    Team addNewMemberToTeam(MemberUpdateDto updateMemberDto);

}
