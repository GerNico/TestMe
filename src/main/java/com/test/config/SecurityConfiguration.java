package com.test.config;

import com.test.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    public SecurityConfiguration(UserService userService, RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.userService = userService;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilderilder) throws Exception {
        authenticationManagerBuilderilder
                .userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login*").permitAll()
                .mvcMatchers("/rest/**")
                .authenticated()
                .and().httpBasic()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .formLogin().loginPage("/login")
                .and()
                .logout().logoutSuccessUrl("/login?logout");
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}