package com.globallogic.dashboard.member;

import java.util.List;
import java.util.Optional;

public interface Memberservice {

     Member createMember(MemberCreateDto memberCreateDto);

     List<Member> getAllMembers();

     Optional<Member> findById(Long id);

     Member findMemberByMemberName(String name);

}
