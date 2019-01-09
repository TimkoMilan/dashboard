package com.globallogic.dashboard.user;

import com.globallogic.dashboard.email.SendEmailService;
import com.globallogic.dashboard.team.TeamRepository;
import com.globallogic.dashboard.validationToken.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserFacade {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleService roleService;
    @Autowired
    private SendEmailService sendEmailService;
    @Autowired
    private TokenService tokenService;



    @Transactional
    public void updateUser(UserUpdateDto userDto, Long id) {
        Optional<User> users = userRepository.findById(id);
        if (users.isPresent()) {
            User user = users.get();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
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
        String type = "registration";
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setCurrentTeam(teamRepository.findTeamById(userDto.getTeamId()));
        String role = userDto.getRoleName();
        user.setRoles(Collections.singleton(roleService.setRole(role)));
        user.setStatus(false);
        userRepository.save(user);

        User user2 = userRepository.findByEmail(userDto.getEmail());
        final String uuid = UUID.randomUUID().toString();
        sendEmailService.sendEmail(uuid,user2,type);
        tokenService.newToken(uuid,user);
    }

    public void resetPassword(String email) {
        String type = "reset";
        final String uuid = UUID.randomUUID().toString();

        User user =userRepository.findByEmail(email);
        sendEmailService.sendEmail(uuid,user,type);
        tokenService.newToken(uuid,user);
    }
    public void changePassword(User user,String password){
        User user1 = userRepository.findByEmail(user.getEmail());
        user1.setPassword(encoder.encode(password));
    }
}