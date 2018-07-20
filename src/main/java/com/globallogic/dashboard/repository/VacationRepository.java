package com.globallogic.dashboard.repository;

import com.globallogic.dashboard.model.Vacation;
import com.globallogic.dashboard.old.VacationTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {


    List<Vacation> findVacationsByMember_SearchString(String searchString);

    List<Vacation> findVacationsByMember_TeamId(Long teamid);


}

