package com.globallogic.dashboard.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
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
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }


    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member findMemberByMemberName(String name) {
        return memberRepository.findMemberByNameIsLike(name);
    }

//    @Override
//    public Member findMemberBySearchString(String searchString) {
//        return null;
//    }

    @Override
    public Member findMemberBySearchString(String searchString) {
        return memberRepository.findMemberBySearchStringIsLike(searchString);
    }


}
