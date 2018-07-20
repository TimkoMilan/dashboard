package com.globallogic.dashboard.repository;

import com.globallogic.dashboard.model.SprintData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintDataRepository extends JpaRepository<SprintData,Long> {

}
