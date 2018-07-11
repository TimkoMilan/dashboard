package com.globallogic.dashboard.repository;

import com.globallogic.dashboard.model.Member;
import com.globallogic.dashboard.model.Team;
import com.globallogic.dashboard.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VacationRepository  extends JpaRepository<Vacation,Long> {
    List<Member> findByMember_Name(String member);
}
