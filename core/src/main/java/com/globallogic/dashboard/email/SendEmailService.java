package com.globallogic.dashboard.email;

import com.globallogic.dashboard.user.User;
import com.globallogic.dashboard.user.UserService;
import com.globallogic.dashboard.validationToken.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserService userService;

    public void sendEmail(String uuid, User user, String type) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("milan.timko@akademiasovy.sk");

        if (type.equals("registration")){
            mail.setSubject("Registration Email");
            mail.setText("Click to confirm registration: http://localhost:8089/api/v1/token/activation/" + uuid);
        }
        else {
            mail.setSubject("Reset password Email");
            mail.setText("Click to change your password: http://localhost:8089/api/v1/token/resetpassword/" + uuid);
        }
        javaMailSender.send(mail);
    }
}
