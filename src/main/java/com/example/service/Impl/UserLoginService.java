package com.example.service.Impl;

import com.example.entity.User;
import com.example.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserLoginService implements UserDetailsService {

    @Resource
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User data = repository.findUserByUsername(username);
        if (data == null) throw new UsernameNotFoundException("用户 " + username + " 登录失败，用户名不存在！");
        return org.springframework.security.core.userdetails.User
                .withUsername(data.getUsername())
                .password(data.getPassword())
                .roles(data.getRole())
                .build();
    }
}
