package com.example;

import com.example.repo.UserRepository;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MatchManageApplicationTests {

    @Resource
    UserRepository repository;

    @Resource
    UserService service;

    @Test
    void contextLoads() {
    }

}
