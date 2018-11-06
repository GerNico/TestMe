package com.test.config;

import com.test.servicies.impl.SubscriberServiceImpl;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SubscriberServiceImpl userServiceImpl;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilderilder) throws Exception {
        authenticationManagerBuilderilder
                .userDetailsService(userServiceImpl)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login*").permitAll()
                .mvcMatchers("/rest/**").permitAll();
//                .authenticated()
//                .and().httpBasic()
//                .authenticationEntryPoint(restAuthenticationEntryPoint)
//                .and()
//                .formLogin().loginPage("/login")
//                .and()
//                .logout().logoutSuccessUrl("/login?logout");
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}