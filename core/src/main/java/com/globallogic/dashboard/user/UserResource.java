package com.globallogic.dashboard.user;

import com.globallogic.dashboard.security.JwtTokenProvider;
import com.globallogic.dashboard.user.payload.LoginResponse;
import com.globallogic.dashboard.user.payload.UserInTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

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


    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            User user = (User) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)).getPrincipal();
            LoginResponse response =
                    new LoginResponse(jwtTokenProvider
                            .createToken(user, userRepository.findByUsername(username).getRoles()));
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new SecurityException("Invalid username/password supplied");
        }
    }

    @PostMapping
    public User newUser(UserDto userDto){
        return userService.newUser(userDto);
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
