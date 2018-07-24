package com.globallogic.dashboard.vacation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {

    List<Vacation> findVacationsByMember_SearchString(String searchString);

    List<Vacation> findVacationsByMember_TeamId(Long teamid);

    List<Vacation> findVacationsByStartIsBetween(Date startDate , Date endDate);

}

