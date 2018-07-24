package com.globallogic.dashboard.member;

import java.util.List;
import java.util.Optional;

public interface Memberservice {

    public Member createMember(MemberCreateDto memberCreateDto);

    public List<Member> getAllMembers();

    public Optional<Member> findById(Long id);

}
