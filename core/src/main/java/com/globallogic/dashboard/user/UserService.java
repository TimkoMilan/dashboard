package com.globallogic.dashboard.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {


    ResponseEntity<?> login(String email, String password);

    User newUser(UserDto userDto);

    UserDetails loadUserByUsername(String username);
}
