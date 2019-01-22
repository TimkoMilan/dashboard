package com.globallogic.dashboard.email;

import com.globallogic.dashboard.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String uuid, User user, String type) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();
        if ((user.getEmail() ==null)){
            throw new UsernameNotFoundException("User with this email doesn´t exis");
        }
        mail.setTo(user.getEmail());
        mail.setFrom("milan.timko@akademiasovy.sk");
        if (type.equals("registration")) {
            mail.setSubject("Registration Email");
            mail.setText("Click to confirm registration: http://172.17.114.22/enableUser/" + uuid);
        } else {
            mail.setSubject("Reset password Email");
            mail.setText("Click to change your password:  http://172.17.114.22/api/v1/token/reset/" + uuid);
        }
        javaMailSender.send(mail);
    }
}