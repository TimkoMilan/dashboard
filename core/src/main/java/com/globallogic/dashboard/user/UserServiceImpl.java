package com.globallogic.dashboard.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(String email, String password) {

        return userRepository.findUsersByUsernameAndPassword(email,password);
    }

    @Override
    public User newUser(UserDto userDto) {

        return null;
    }
}
