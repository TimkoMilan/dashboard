package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.sprint.SprintData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintDataRepository extends JpaRepository<SprintData,Long> {

}
