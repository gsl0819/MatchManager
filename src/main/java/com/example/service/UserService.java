package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface UserService {
    boolean creatUser(String username, String password,String role);
    List<User> selectAllUser();

    boolean deletUser(int userid);

    boolean updateUser(int userid, String username);

    String getRoleByUsername(String username);

    int countsum();
}
