package com.globallogic.dashboard.user;

import java.util.List;

public interface UserService {

    UserDto newUser(UserDto userDto);
    Boolean updateTeam(Long userId, Long teamId);
    UserDto findById(Long userId);
    List<UserDto> getAll();

}
