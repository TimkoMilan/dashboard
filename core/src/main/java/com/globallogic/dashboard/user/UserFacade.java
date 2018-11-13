package com.globallogic.dashboard.user;

import com.globallogic.dashboard.common.ServiceException;
import com.globallogic.dashboard.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserFacade {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PasswordEncoder encoder;

    public void updateUser(UserCreateDto userDto, Long id) {
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
            if (role.equals("ROLE_ADMIN")) {
                Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> new ServiceException("User Role has not been set."));
                user.setRoles(Collections.singleton(userRole));
            } else if (role.equals("ROLE_USER")) {
                Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new ServiceException("Admin Role has not been set."));
                user.setRoles(Collections.singleton(userRole));
            } else if (role.equals("ROLE_TEAMLEADER")) {
                Role userRole = roleRepository.findByName(RoleName.ROLE_TEAMLEADER).orElseThrow(() -> new ServiceException("TeamLeader Role has not been set."));
                user.setRoles(Collections.singleton(userRole));
            }
            userRepository.save(user);
        }


    }
}
