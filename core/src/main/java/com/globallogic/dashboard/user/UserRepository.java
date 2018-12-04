package com.globallogic.dashboard.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByEmail(String username);

    User findByEmail(String email);

    @Query( countQuery = "SELECT count (ROLE_ID) from USER_ROLES INNER JOIN ROLE ON USER_ROLES.ROLE_ID=ROLE.id INNER join User ON USER_ROLES.USER_ID = USER.ID WHERE USER_ROLES.ROLE_ID =2")
    Integer countAllByRoles(Role roleAdmin);

}
