package com.globallogic.dashboard.user;

public interface UserService {


    User login(String email,String password);

    User newUser(UserDto userDto);

}
