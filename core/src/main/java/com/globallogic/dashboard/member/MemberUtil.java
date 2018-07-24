package com.globallogic.dashboard.member;

public final class MemberUtil {
    private MemberUtil() {
    }

    public static Member fromCreate(MemberCreateDto memberCreateDto){

        Member member = new Member();
        member.setName(memberCreateDto.getName());
        member.setFocus(memberCreateDto.getFocus());
        member.setPosition(memberCreateDto.getPosition());
        member.setBillingValue(memberCreateDto.getBillingValue());
        String searchname= ((memberCreateDto.getName()).toLowerCase()).replace(" ","");
        member.setSearchString(searchname);
        return  member;

    }


}
