package com.globallogic.dashboard.user;

import java.util.List;

public interface UserService {

    UserDto newUser(UserCreateDto userDto);
    Boolean updateTeam(Long userId, Long teamId);
    UserDto findById(Long userId);
    List<UserDto> getAll();
    void removeUser(Long id);
    UserDto updateUserData(UserCreateDto userDto,Long id);
}
