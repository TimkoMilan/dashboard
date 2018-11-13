package com.globallogic.dashboard.user;

import com.globallogic.dashboard.security.JwtTokenProvider;
import com.globallogic.dashboard.user.payload.LoginResponse;
import com.globallogic.dashboard.user.payload.UserInTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import com.globallogic.dashboard.security.SecurityException;


import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private UserFacade userFacade;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            User user = (User) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password)).getPrincipal();
            LoginResponse response =
                    new LoginResponse(jwtTokenProvider
                            .createToken(user, new ArrayList<>(userRepository.findByEmail(email).getRoles())));
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new SecurityException("Invalid username/password supplied");
        }
    }

    @PostMapping("addRegularUser")
    public void addRegularUser(@RequestBody UserCreateDto userDto){
        if(userRepository.existsByEmail(userDto.getEmail())) {
//            return new ResponseEntity(new ApiResponse(false, "A user with the same username already" +
//                    "exists. Select another email."),
//                    HttpStatus.BAD_REQUEST);
        }
        userService.newUser(userDto);
//        UserDto userRegisterResponse = userService.newUser(userDto);
//        return ResponseEntity.ok(userRegisterResponse);
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

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable(value = "id") Long id){
         userService.removeUser(id);
    }

    @PutMapping("/{id}")
    public void updateUserData(@PathVariable(value = "id") Long id ,@RequestBody UserCreateDto userDto){
        userService.updateUserData(userDto,id);
    }

    @GetMapping("{userId}")
    public UserDto getUserById(@PathVariable(value = "userId") Long userId){
        return userService.findById(userId);
    }

    @GetMapping("getAll")
    public List<UserDto> getAllUser(){
        return userService.getAll();
    }
}
