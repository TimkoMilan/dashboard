package com.globallogic.dashboard.user;

import com.globallogic.dashboard.common.ApiResponse;
import com.globallogic.dashboard.security.JwtTokenProvider;
import com.globallogic.dashboard.user.payload.LoginResponse;
import com.globallogic.dashboard.user.payload.UpdateTeamRequestDto;
import com.globallogic.dashboard.user.payload.UserInTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import com.globallogic.dashboard.security.SecurityException;


import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("users")
public class UserResource {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            User user = (User) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)).getPrincipal();
            LoginResponse response =
                    new LoginResponse(jwtTokenProvider
                            .createToken(user, new ArrayList<>(userRepository.findByUsername(username).getRoles())));
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new SecurityException("Invalid username/password supplied");
        }
    }

    @PostMapping("addRegularUser")
    public ResponseEntity<?> addRegularUser(@RequestBody UserDto userDto){
        if(userRepository.existsByUsername(userDto.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "A user with the same username already" +
                    "exists. Select another username."),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(userDto.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "A user with the same email already" +
                    "exists. Select another email."),
                    HttpStatus.BAD_REQUEST);
        }
        UserDto userRegisterResponse = userService.newUser(userDto);
        return ResponseEntity.ok(userRegisterResponse);
    }

    @PostMapping("updateUserTeam")
    public ResponseEntity<?> updateUserTeam(@RequestBody UpdateTeamRequestDto updateTeamRequestDto){
        if(userService.updateTeam(updateTeamRequestDto.getUserId(), updateTeamRequestDto.getTeamId()))
        {
            return ResponseEntity.ok(new ApiResponse(true, "User updated"));
        }
        else{
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Failed updating user"));
        }
    }

    @GetMapping("getUserNameFromToken")
    public String getUserNameFromToken(ServletRequest req){
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        return jwtTokenProvider.getUsername(token);
    }

    @GetMapping("getEmailFromToken")
    public String getEmailFromToken(ServletRequest req){
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        return jwtTokenProvider.getEmail(token);
    }

    @GetMapping("getUserFromToken")
    public ResponseEntity<UserInTokenResponse> getUserFromToken(ServletRequest req){
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        return ResponseEntity.ok(new UserInTokenResponse(jwtTokenProvider.getUsername(token),
                                                        jwtTokenProvider.getEmail(token)));
    }
    
}
