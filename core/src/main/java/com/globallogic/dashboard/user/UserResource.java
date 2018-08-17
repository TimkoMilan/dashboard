package com.globallogic.dashboard.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserResource {

    @Autowired
    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> login (@RequestParam  String email, @RequestParam String password){
        return userService.login(email,password);
    }
    @PostMapping
    public User newUser(UserDto userDto){
        return userService.newUser(userDto);
    }

}
