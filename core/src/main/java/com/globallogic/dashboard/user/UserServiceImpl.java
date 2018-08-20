package com.globallogic.dashboard.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User newUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
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
