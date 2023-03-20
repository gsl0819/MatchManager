package com.example.service.Impl;

import com.example.entity.User;
import com.example.repo.UserRepository;
import com.example.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

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
        return repository.findUsersByUseridNot(1);
    }

    @Override
    public boolean deletUser(int userid) {
        return repository.deleteUserByUserid(userid) == 1;

    }
}
