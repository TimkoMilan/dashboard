package com.globallogic.dashboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/actuator/health","/actuator/info").hasRole("ADMIN")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        UserDetails build = User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build();
        auth
                .inMemoryAuthentication()
                .withUser(build);
        UserDetails buildAdmin = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN").build();
        auth
                .inMemoryAuthentication()
                .withUser(buildAdmin);
    }


    public void configure(WebSecurity web){
        web.ignoring()
                .antMatchers("/swagger-ui.html")
                .antMatchers("/**");
    }
}
