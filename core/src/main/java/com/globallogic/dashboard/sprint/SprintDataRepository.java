package com.globallogic.dashboard.sprint;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintDataRepository extends QuerydslPredicateExecutor<SprintData>, QuerydslBinderCustomizer<QSprintData>, JpaRepository<SprintData, Long> {


    List<SprintData> findAllByTeam_Name(String teamName);

    List<SprintData> findAllByTeam_NameAndSprint_Name(String teamName, String sprintId);

//    List<SprintData> findOrOrderBySprintStartDate(Predicate queryPredicate);

    @Override
    default void customize(QuerydslBindings bindings, QSprintData root) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

    }
}
