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
import java.util.Set;
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

    private UserFacade userFacade;

    @Override
    public UserDto newUser(UserCreateDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));

//        Role userRole= userFacade.settingRole(role);
//        user.setRoles(Collections.singleton(userRole));

        String role = userDto.getRoleName();
        if (role.equals("ROLE_ADMIN")){
            Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> new ServiceException("User Role has not been set."));
            user.setRoles(Collections.singleton(userRole));
        }else if (role.equals("ROLE_USER")){
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new ServiceException("Admin Role has not been set."));
            user.setRoles(Collections.singleton(userRole));
        }else if (role.equals("ROLE_TEAMLEADER")){
            Role userRole = roleRepository.findByName(RoleName.ROLE_TEAMLEADER).orElseThrow(() -> new ServiceException("TeamLeader Role has not been set."));
            user.setRoles(Collections.singleton(userRole));
        }


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
    public void updateUserData(UserCreateDto userDto, Long id) {
        Optional<User> users =  userRepository.findById(id);
        if (users.isPresent()){
            User user = users.get();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPassword(userDto.getPassword());
            user.setCurrentTeam(teamRepository.findTeamById(userDto.getTeamId()));
            user.setEmail(userDto.getEmail());
            //TODO create runable facade without duplicate code
            String role = userDto.getRoleName();
            if (role.equals("ROLE_ADMIN")){
                Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> new ServiceException("User Role has not been set."));
                user.setRoles(Collections.singleton(userRole));
            }else if (role.equals("ROLE_USER")){
                Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new ServiceException("Admin Role has not been set."));
                user.setRoles(Collections.singleton(userRole));
            }else if (role.equals("ROLE_TEAMLEADER")){
                Role userRole = roleRepository.findByName(RoleName.ROLE_TEAMLEADER).orElseThrow(() -> new ServiceException("TeamLeader Role has not been set."));
                user.setRoles(Collections.singleton(userRole));
            }
            userRepository.save(user);
        }
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
