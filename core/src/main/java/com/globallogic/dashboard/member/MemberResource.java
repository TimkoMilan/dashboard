package com.globallogic.dashboard.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("member")
public class MemberResource {

    @Autowired
    private Memberservice memberService;
    @Autowired
    private MemberFacadeImpl memberFacade;


    @PostMapping
    public Member createMember (MemberCreateDto memberCreateDto){
       return memberService.createMember(memberCreateDto);
       }

     @GetMapping
    public List<Member> getAllMember(){
        return memberService.getAllMembers();
     }

     @PutMapping("/{memberId}/team/assign/{teamId}")
     public void assignToTeam( Long memberId, Long teamId){
         memberFacade.assignToTeam(memberId,teamId);
     }

}
