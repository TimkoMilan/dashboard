package com.globallogic.dashboard.user;


import com.globallogic.dashboard.common.ServiceException;
import com.globallogic.dashboard.team.Team;
import com.globallogic.dashboard.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public UserDto newUser(UserCreateDto userDto) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
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
        User user = userRepository.getOne(userId);
        return UserUtil.convertUserToUserDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserUtil::convertUserToUserDto).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "users")
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found for email:" + email);
        }
        return user;
    }
}
