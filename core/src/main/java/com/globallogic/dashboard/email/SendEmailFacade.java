package com.globallogic.dashboard.email;

import com.globallogic.dashboard.user.User;
import com.globallogic.dashboard.user.UserActivationDto;
import com.globallogic.dashboard.user.UserFacade;
import com.globallogic.dashboard.user.UserService;
import com.globallogic.dashboard.validationToken.Token;
import com.globallogic.dashboard.validationToken.TokenRepository;
import com.globallogic.dashboard.validationToken.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SendEmailFacade {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserFacade userFacade;

    public ResponseEntity activation(UserActivationDto dto,String token) {
        Token token1 = tokenRepository.findByToken(token);
        
        Date expDate = token1.getExpirationDate();

        if (CheckDateUtil.checkDateValidation(expDate)) {
            userService.removeUser(token1.getUser().getId());
            return (ResponseEntity) ResponseEntity.status(403);
        } else {
            token1.getUser().setStatus(true);
            token1.getUser().setPassword(encoder.encode(dto.getPassword()));
            tokenService.removeToken(token);
            return ResponseEntity.ok(200);
        }
    }

    public ResponseEntity resetPassword(ResetPasswordDto dto) {
        Token token1 = tokenRepository.findByToken(dto.getToken());
        User user=token1.getUser();
        String password = dto.getPassword();
        String token = dto.getToken();
        Date expDate = token1.getExpirationDate();
        if (CheckDateUtil.checkDateValidation(expDate)){
            ResponseEntity.status(403);
            System.out.println("token has been expirated");
        }else{
            userFacade.changePassword(user,password);
            tokenService.removeToken(token);
            ResponseEntity.ok(200);
        }
        return null;
        }
}
