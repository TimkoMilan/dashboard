package com.globallogic.dashboard.user;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> login(String email, String password) {
        User user = userRepository.findUsersByUsernameAndPassword(email, password);
        if (user == null){
            JSONObject responseJsonObject = new JSONObject();
            responseJsonObject.put("message", "User with specified login details does not exist.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseJsonObject.toString());
        }else {
            return ResponseEntity.ok(user);
        }

    }

    @Override
    public User newUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getPassword());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        final User byUsername = userRepository.findByUsername(username);
        if (byUsername == null) {
            throw new UsernameNotFoundException("User not found for username:" + username);
        }
        return byUsername;
    }
}
