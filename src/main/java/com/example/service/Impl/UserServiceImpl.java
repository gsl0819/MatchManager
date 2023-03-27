package com.example.service.Impl;

import com.example.entity.User;
import com.example.repo.UserRepository;
import com.example.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository repository;

    @Override
    public boolean creatUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        user.setRole(role);
        try {
            repository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public List<User> selectAllUser() {
        return repository.findUsersByUseridNot(1);
    }

    @Override
    public boolean deletUser(int userid) {
        return repository.deleteUserByUserid(userid) == 1;

    }

    @Override
    public boolean updateUser(int userid, String username) {
        Optional<User> op = repository.findById(userid);
        AtomicReference<User> usertest = new AtomicReference<>(new User());
        op.ifPresent(user -> {
            user.setUsername(username);
            usertest.set(repository.save(user));
        });
        if (usertest == null) return false;
        else return true;
    }

    @Override
    public String getRoleByUsername(String username) {
        return repository.findUserByUsername(username).getRole();
    }

    @Override
    public int countsum() {
        return repository.countUsersByUseridIsNotNull();
    }

}
