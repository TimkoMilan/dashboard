//package com.globallogic.dashboard.email;
//
//import com.globallogic.dashboard.user.UserCreateDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SendEmailService {
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    public void sendEmail(UserCreateDto userDto)throws MailException{
//
//        SimpleMailMessage mail = new SimpleMailMessage();
////        mail.setTo("milan.timko997@gmail.com");x
//        mail.setTo(userDto.getEmail());
//
//        mail.setFrom("milan.timko@akademiasovy.sk");
//        mail.setSubject("Registration Email");
//
//        String token = "abcdefgh";
//
//        mail.setText("Click to confirm registration: "+token+"");
//
//        javaMailSender.send(mail);
//
//    }
//}
