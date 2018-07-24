package com.globallogic.dashboard.member;

import java.util.List;

public interface MemberFacade {

    Member createMember (MemberCreateDto memberCreateDto);

    List<Member> getAllMember();

    void assignToTeam(Long memberId, Long teamId);


}
