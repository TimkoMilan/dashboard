package com.globallogic.dashboard.user;

public interface UserService {

    UserDto newUser(UserDto userDto);
    Boolean updateTeam(Long userId, Long teamId);
    void removeUser(Long id);

    void updateUserData(UserDto userDto,Long id);
}
