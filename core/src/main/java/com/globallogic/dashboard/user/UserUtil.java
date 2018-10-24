package com.globallogic.dashboard.user;

public  class UserUtil {

    private UserUtil(){}


    public static UserDto convertUserToUserDto(final User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

}
