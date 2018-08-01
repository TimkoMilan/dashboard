package com.globallogic.dashboard.sprint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintDataRepository extends JpaRepository<SprintData,Long> {

    List<SprintData> findAllBySprint_Name(String sprintName);
    List<SprintData>findAllByTeam_Name(String teamName);

}
