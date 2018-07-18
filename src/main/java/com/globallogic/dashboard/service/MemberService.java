package com.globallogic.dashboard.service;

import com.globallogic.dashboard.event.VacationEventListener;
import com.globallogic.dashboard.model.Member;
import com.globallogic.dashboard.repository.MemberRepository;
import com.globallogic.dashboard.service.util.MemberUtil;
import com.globallogic.dashboard.to.MemberCreateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberService {

    private static final Logger log = LoggerFactory.getLogger(VacationEventListener.class);

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Member createMember(MemberCreateDto memberCreateDto){
        String name = memberCreateDto.getName();
        if (memberRepository.findMemberByNameIsLike(name)==null){
            Member member = MemberUtil.fromCreate(memberCreateDto);
            return memberRepository.save(member);
        }else
        log.error("User already exist");
        return memberRepository.findMemberByNameIsLike(name);
    }

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

}
