package com.globallogic.dashboard.vacation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VacationRepository extends QuerydslPredicateExecutor<Vacation>, QuerydslBinderCustomizer<QVacation>, JpaRepository<Vacation, Long> {

    List<Vacation> findVacationsByMember_SearchString(String searchString);

    List<Vacation> findVacationsByMember_TeamId(Long teamid);

    List<Vacation> findVacationsByStartIsBetween(Date startDate, Date endDate);

    @Query("SELECT v FROM Vacation v where v.start between ?1 AND ?2 or v.end  between ?1 AND ?2")
    List<Vacation> findVacationsByStartOrEndIsBetween(Date startDate, Date endDate);

    List<Vacation> findAllByStartAndMember_Name(Date startDate, String memberName);
}
