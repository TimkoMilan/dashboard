package com.globallogic.dashboard.resource;

import com.globallogic.dashboard.model.Member;
import com.globallogic.dashboard.service.MemberService;
import com.globallogic.dashboard.to.MemberCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("member")
public class MemberResource {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public Member createMember (MemberCreateDto memberCreateDto){
       return memberService.createMember(memberCreateDto);
       }

     @GetMapping
    public List<Member> getAllMember(){
        return memberService.getAllMembers();
     }

}
