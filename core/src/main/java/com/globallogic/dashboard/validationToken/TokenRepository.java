package com.globallogic.dashboard.validationToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository <Token,Long> {

    Token findByToken(String token);

    void deleteTokenByToken(String token);
    void deleteTokenByUserId(Long id);
}
