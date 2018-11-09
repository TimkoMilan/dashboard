package com.globallogic.dashboard.user;

public  class UserUtil {

    private UserUtil(){}


    public static UserDto convertUserToUserDto(final User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }

}
