package com.globallogic.dashboard.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("member")
public class MemberResource {

    @Autowired
    private MemberFacade memberFacade;


    @PostMapping
    public Member createMember (MemberCreateDto memberCreateDto){
       return memberFacade.createMember(memberCreateDto);
       }

     @GetMapping
    public List<MemberDto> getAllMember(){
        return memberFacade.getAllMembers();
     }

     @PutMapping("/{memberId}/team/assign/{teamId}")
     public void assignToTeam( Long memberId, Long teamId){
         memberFacade.assignToTeam(memberId,teamId);
     }

}
