package com.globallogic.dashboard.user;

public  class UserUtil {

    private UserUtil(){}


    public static UserDto convertUserToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRoles(user.getRoles());
        if (user.getCurrentTeam()!=null){
            userDto.setTeamId(user.getCurrentTeam().getId());
            userDto.setTeamName(user.getCurrentTeam().getName());
        }
        return userDto;
    }

}
