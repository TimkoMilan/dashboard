package com.globallogic.dashboard.repository;

import com.globallogic.dashboard.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

        Member findMemberByNameIsLike(String member);



}
