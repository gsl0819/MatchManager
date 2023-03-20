package com.example.configuration;

import com.example.service.Impl.UserLoginService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    UserLoginService service;
    @Resource
    DataSource source;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/static/**", "/register", "/api/auth/**").permitAll()
                .anyRequest().hasRole("管理员")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .permitAll()
                .defaultSuccessUrl("/index", true)
                .and()
                .logout()
                .logoutUrl("/api/auth/logout")
                .logoutSuccessUrl("/api/auth/logout-success")
                .and()
                .rememberMe()
                .tokenRepository(new JdbcTokenRepositoryImpl() {{
                    setDataSource(source);
                }})
                .and()
                .csrf()
                .disable()
                .cors();
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(service)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
