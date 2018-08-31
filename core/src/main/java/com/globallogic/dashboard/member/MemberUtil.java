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
        String searchName= toSearchString((memberCreateDto.getName()));
        member.setSearchString(searchName);
        return  member;
    }


    public static MemberDto createDto(Member member){
        MemberDto memberDto = new MemberDto();
        memberDto.setName(member.getName());
        memberDto.setSearchString(member.getSearchString());
        return memberDto;
    }

    public static String toSearchString(String name){
        return name.toLowerCase().replace(" ","");
    }


}
