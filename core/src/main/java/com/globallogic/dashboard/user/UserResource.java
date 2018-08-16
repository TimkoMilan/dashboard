package com.globallogic.dashboard.user;

import org.springframework.beans.factory.annotation.Autowired;
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
    public User login (@RequestParam  String email,@RequestParam String password){
        userService.login(email,password);
        return null;
    }
    @PostMapping
    public User newUser(UserDto userDto){
        return userService.newUser(userDto);
    }

}
