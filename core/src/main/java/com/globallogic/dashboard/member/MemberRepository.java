package com.globallogic.dashboard.member;

import com.globallogic.dashboard.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

        Member findMemberByNameIsLike(String member);



}
