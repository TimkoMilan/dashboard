package com.globallogic.dashboard.email;

import com.globallogic.dashboard.user.UserActivationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("token")
public class SendEmailResource {

    @Autowired
    private SendEmailFacade sendEmailFacade;


    @PostMapping("/activation/{token}")
    public ResponseEntity sendingEmail(@PathVariable(value = "token") String token,@RequestBody UserActivationDto dto) {
          return sendEmailFacade.activation(dto,token);
    }

    @PostMapping("/reset")
    public ResponseEntity resetPassword(@RequestBody ResetPasswordDto dto){
        return sendEmailFacade.resetPassword(dto);
    }
}
