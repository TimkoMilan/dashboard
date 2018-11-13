package com.globallogic.dashboard.user;

import com.globallogic.dashboard.common.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role setRole(String role){

        if (role.equals("ROLE_ADMIN")) {
            return roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> new ServiceException("Admin Role has not been set."));
        } else if (role.equals("ROLE_USER")) {
            return roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new ServiceException("User Role has not been set."));
        } else if (role.equals("ROLE_TEAMLEADER")) {
            return roleRepository.findByName(RoleName.ROLE_TEAMLEADER).orElseThrow(() -> new ServiceException("TeamLeader Role has not been set."));
        }
        return null;
    }
}
