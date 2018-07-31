package com.globallogic.dashboard.sprint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Sprint,Long> {
    Sprint findByName(String name);

    @Query("SELECT s FROM Sprint s where s.start between ?1 AND ?2 OR s.end between ?1 AND ?2 ")
    List<Sprint> findSprintsByStartOrEndIsBetween(Date startDate,Date endDate);
}
