package com.globallogic.dashboard.user;

import com.globallogic.dashboard.team.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserFacade {

    private static final Logger log = LoggerFactory.getLogger(UserFacade.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleService roleService;

    @Transactional
    public void updateUser(UserUpdateDto userDto, Long id) {
        Optional<User> users = userRepository.findById(id);
        if (users.isPresent()) {
            User user = users.get();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            if (userDto.getPassword()!=null){
                user.setPassword(encoder.encode(userDto.getPassword()));
            }
            if (userDto.getTeamId()!=null){
                user.setCurrentTeam(teamRepository.findTeamById(userDto.getTeamId()));
            }
            user.setEmail(userDto.getEmail());
            String role = userDto.getRoleName();

            user.setRoles(Collections.singleton(roleService.setRole(role)));

        }
    }

    @Transactional
    public void createUser(UserCreateDto userDto){
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));

        user.setCurrentTeam(teamRepository.findTeamById(userDto.getTeamId()));
        String role = userDto.getRoleName();
        user.setRoles(Collections.singleton(roleService.setRole(role)));
        userRepository.save(user);
    }
}