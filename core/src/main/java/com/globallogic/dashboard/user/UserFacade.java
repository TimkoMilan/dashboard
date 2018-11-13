package com.globallogic.dashboard.user;

import com.globallogic.dashboard.common.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class UserFacade {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

     public void setRole(String role, User user){
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
