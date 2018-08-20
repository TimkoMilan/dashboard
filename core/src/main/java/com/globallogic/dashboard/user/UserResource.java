package com.globallogic.dashboard.user;

import com.globallogic.dashboard.security.JwtTokenProvider;
import com.globallogic.dashboard.user.payload.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserResource {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("doLogin")
    public ResponseEntity<LoginResponse> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            LoginResponse response =
                    new LoginResponse(jwtTokenProvider
                            .createToken(username, userRepository.findByUsername(username).getRoles()));
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new SecurityException("Invalid username/password supplied");
        }
    }

    @PostMapping
    public User newUser(UserDto userDto){
        return userService.newUser(userDto);
    }




}
