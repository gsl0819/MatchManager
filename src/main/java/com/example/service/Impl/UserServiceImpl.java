package com.example.service.Impl;

import com.example.entity.User;
import com.example.repo.UserRepository;
import com.example.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Resource
    UserRepository repository;

    @Override
    public void creatUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        user.setRole(role);
        repository.save(user);
    }

    @Override
    public List<User> selectAllUser() {
        List<User> userList = repository.findUsersByUseridNot(1);
        return userList;
    }

    @Override
    public boolean deletUser(int userid) {
        if (repository.deleteUserByUserid(userid)==1)
            return true;
        else return false;

    }

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
