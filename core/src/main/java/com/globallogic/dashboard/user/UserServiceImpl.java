package com.globallogic.dashboard.user;


import com.globallogic.dashboard.common.ServiceException;
import com.globallogic.dashboard.team.Team;

import com.globallogic.dashboard.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private RoleRepository roleRepository;

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
        Optional<User> users=userRepository.findById(id);

        if (users.isPresent()){
            User user = users.get();

            Set<Role> roles = user.getRoles();
            for (Role role1 : roles) {
                if (role1.getName().toString().equals("ROLE_ADMIN")){
                    Optional<Role> userRoles = roleRepository.findByName(RoleName.ROLE_ADMIN);
                    if (userRoles.isPresent()){
                        Role role = userRoles.get();
                        if (userRepository.countAllByRoles(role)>1){
                            userRepository.deleteById(id);
                        }
                    }
                }else {
                    userRepository.deleteById(id);
                }
            }
        }
    }
    @Override
    public void updateUserData(UserCreateDto userDto, Long id) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user=userRepository.findByEmail(username);
        final User byUsername = userRepository.findByEmail(user.getEmail());
        if (byUsername == null) {
            throw new UsernameNotFoundException("User not found for username:" + username);
        }
        return byUsername;
    }
}
