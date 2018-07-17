package com.globallogic.dashboard.service.util;

import com.globallogic.dashboard.model.Member;
import com.globallogic.dashboard.to.MemberCreateDto;

public final class MemberUtil {
    public static Member fromCreate(MemberCreateDto memberCreateDto){

        Member member = new Member();
        member.setName(memberCreateDto.getName());
        member.setFocus(memberCreateDto.getFocus());
        member.setPosition(memberCreateDto.getPosition());
        member.setBillingValue(memberCreateDto.getBillingValue());
        String searchname= ((memberCreateDto.getName()).toLowerCase()).trim();
        member.setSearchString(searchname);
        return  member;

    }

}
