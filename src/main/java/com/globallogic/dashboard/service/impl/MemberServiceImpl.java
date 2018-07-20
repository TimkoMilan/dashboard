package com.globallogic.dashboard.service.impl;

import com.globallogic.dashboard.event.VacationEventListener;
import com.globallogic.dashboard.model.Member;
import com.globallogic.dashboard.repository.MemberRepository;
import com.globallogic.dashboard.service.Memberservice;
import com.globallogic.dashboard.service.util.MemberUtil;
import com.globallogic.dashboard.to.MemberCreateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberServiceImpl implements Memberservice {

    private static final Logger log = LoggerFactory.getLogger(VacationEventListener.class);
    @Autowired
    private MemberRepository memberRepository;


    @Override
    public Member createMember(MemberCreateDto memberCreateDto) {
        String name = memberCreateDto.getName();
        if (memberRepository.findMemberByNameIsLike(name) == null) {
            Member member = MemberUtil.fromCreate(memberCreateDto);
            return memberRepository.save(member);
        } else
            log.error("User already exist");
        return memberRepository.findMemberByNameIsLike(name);
    }

    @Override
    public List<Member> getAllMembers()  {
        return memberRepository.findAll();
    }


    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

}
