package com.globallogic.dashboard.user;


import com.globallogic.dashboard.common.ServiceException;
import com.globallogic.dashboard.team.Team;

import com.globallogic.dashboard.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PasswordEncoder encoder;

    private UserUtil userUtil;

    @Override
    public UserDto newUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new ServiceException("User Role has not been set."));
        user.setRoles(Collections.singleton(userRole));
        user.setCurrentTeam(teamRepository.findTeamById(userDto.getTeamId()));

        User result = userRepository.save(user);
        return UserUtil.convertUserToUserDto(result);
    }

    @Override
    public Boolean updateTeam(Long userId, Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ServiceException("Team does not exist"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException("User does not exist"));
        user.setCurrentTeam(team);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDto findById(Long userId) {
        User user=userRepository.getOne(userId);
        return UserUtil.convertUserToUserDto(user);

    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserUtil::convertUserToUserDto).collect(Collectors.toList());
    }




    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUserData(UserDto userDto, Long id) {
        Optional<User> users =  userRepository.findById(id);
        if (users.isPresent()){
            User user = users.get();
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setCurrentTeam(teamRepository.findTeamById(userDto.getTeamId()));
            user.setEmail(userDto.getEmail());
            userRepository.save(user);
        }
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
