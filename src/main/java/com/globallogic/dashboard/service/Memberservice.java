package com.globallogic.dashboard.service;

import com.globallogic.dashboard.model.Member;
import com.globallogic.dashboard.to.MemberCreateDto;

import java.util.List;
import java.util.Optional;

public interface Memberservice {

    public Member createMember(MemberCreateDto memberCreateDto);

    public List<Member> getAllMembers();

    public Optional<Member> findById(Long id);

}
