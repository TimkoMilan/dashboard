package com.globallogic.dashboard.email;

import com.globallogic.dashboard.user.UserActivationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("token")
public class SendEmailResource {

    @Autowired
    private SendEmailFacade sendEmailFacade;


    @PostMapping("/activation/")
    public ResponseEntity sendingEmail(@RequestBody UserActivationDto dto) {
        return sendEmailFacade.activation(dto);
    }

    @PostMapping("/reset")
    public ResponseEntity resetPassword(@RequestBody ResetPasswordDto dto){
        return sendEmailFacade.resetPassword(dto);
    }
}
