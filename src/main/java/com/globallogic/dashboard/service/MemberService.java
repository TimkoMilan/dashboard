package com.globallogic.dashboard.service;

import com.globallogic.dashboard.model.Member;
import com.globallogic.dashboard.repository.MemberRepository;
import com.globallogic.dashboard.service.util.MemberUtil;
import com.globallogic.dashboard.to.MemberCreateDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @PostMapping
    public Member createMember(MemberCreateDto memberCreateDto){
        Member member = MemberUtil.fromCreate(memberCreateDto);
        return memberRepository.save(member);
    }

    @GetMapping
    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }
}
