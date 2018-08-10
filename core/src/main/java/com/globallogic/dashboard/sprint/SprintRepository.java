package com.globallogic.dashboard.sprint;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SprintRepository extends QuerydslPredicateExecutor<Sprint>, QuerydslBinderCustomizer<QSprint>, JpaRepository<Sprint,Long> {

    Sprint findAllByName(String sprintsName);

    @Query("SELECT s FROM Sprint s where s.start between ?1 AND ?2 OR s.end between ?1 AND ?2 ")
    List<Sprint> findSprintsByStartOrEndIsBetween(Date startDate,Date endDate);

    @Override
    default void customize(QuerydslBindings bindings, QSprint root) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

    }
}
