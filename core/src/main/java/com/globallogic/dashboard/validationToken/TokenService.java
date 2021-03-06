package com.globallogic.dashboard.validationToken;

import com.globallogic.dashboard.common.CheckDateUtil;
import com.globallogic.dashboard.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class TokenService implements Serializable {

    @Autowired
    private TokenRepository tokenRepository;

    public void newToken(String uuid, User user) {
        Date currentDay = new Date();
        Date dayAfter = new Date(currentDay.getTime() + TimeUnit.DAYS.toMillis(1));

        Token token = new Token();
        token.setToken(uuid);
        token.setUser(user);
        token.setCreationDate(currentDay);
        token.setExpirationDate(dayAfter);
        tokenRepository.save(token);
    }

    public void removeToken(String token) {
        tokenRepository.deleteTokenByToken(token);
    }

    public void checkTokenValidation() {
        List<Token> tokens = tokenRepository.findAll();
        for (Token token : tokens) {
            if (CheckDateUtil.checkDateValidation(token.getExpirationDate())) {
                tokenRepository.delete(token);
            }
        }
    }

    public void removeTokenByUserId(Long id) {
        tokenRepository.deleteTokenByUserId(id);
    }
}
