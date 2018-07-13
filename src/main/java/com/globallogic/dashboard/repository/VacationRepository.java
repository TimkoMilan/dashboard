package com.globallogic.dashboard.repository;

import com.globallogic.dashboard.model.Member;
import com.globallogic.dashboard.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository  extends JpaRepository<Vacation,Long> {


}

