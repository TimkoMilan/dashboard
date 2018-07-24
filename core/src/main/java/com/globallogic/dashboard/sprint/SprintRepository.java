package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.sprint.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint,Long> {

}
