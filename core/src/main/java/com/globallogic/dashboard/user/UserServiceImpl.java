package com.globallogic.dashboard.user;

import com.globallogic.dashboard.common.ServiceException;
import com.globallogic.dashboard.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public UserDto newUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new ServiceException("User Role has not been set."));
        user.setRoles(Collections.singleton(userRole));
        user.setTeam(teamRepository.findTeamById(userDto.getTeamId()));

        User result = userRepository.save(user);
        return UserUtil.convertUserToUserDto(result);
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
