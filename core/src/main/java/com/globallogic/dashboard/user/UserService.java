package com.globallogic.dashboard.user;

import java.util.List;

public interface UserService {

    UserDto newUser(UserCreateDto userDto);
    User findByEmail(String email);
    Boolean updateTeam(Long userId, Long teamId);
    UserDto findById(Long userId);
    List<UserDto> getAll();
    void removeUser(Long id);
    void updateUserData(UserCreateDto userDto,Long id);
}
