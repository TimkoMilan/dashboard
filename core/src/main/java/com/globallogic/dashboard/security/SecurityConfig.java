package com.globallogic.dashboard.security;

import com.globallogic.dashboard.user.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }



    private static final String[] AUTH_WHITELIST = {

            // -- swagger ui
            "/**/swagger-resources/**",
            "/**/swagger-ui.html",
            "/**/v2/api-docs",
            "/**/webjars/**",
            "/**/h2-console/**"
    };


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .antMatchers("/",
                            "/favicon.ico",
                            "/**/*.png",
                            "/**/*.gif",
                            "/**/*.svg",
                            "/**/*.jpg",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js")
                            .permitAll()
                    .antMatchers(AUTH_WHITELIST)
                        .permitAll()
                    //TODO remove later, permitting now all requests to make it easier to work for front-end
                    /*.antMatchers("/**")
                        .permitAll()*/
                    .antMatchers("/**/users/login/**")
                        .permitAll()
                    .antMatchers("/**/users/addRegularUser/**")
                        .hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .apply(new JwtTokenFilterConfigurer(jwtTokenProvider))
                    .and().headers().frameOptions().disable()
                    .and()
                    .csrf().disable();
    }


}

