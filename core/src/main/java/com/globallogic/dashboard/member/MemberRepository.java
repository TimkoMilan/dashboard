package com.globallogic.dashboard.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

        Member findMemberByNameIsLike(String member);
        Member findMemberBySearchStringIsLike(String searchString);



}
