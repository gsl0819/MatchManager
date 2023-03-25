package com.example;

import com.example.entity.Club;
import com.example.repo.ClubRepository;
import com.example.repo.MatchRepository;
import com.example.repo.UserRepository;
import com.example.service.ClubService;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MatchManageApplicationTests {

    @Resource
    UserRepository userRepository;
    @Resource
    ClubRepository clubRepository;
    @Resource
    MatchRepository matchRepository;

    @Resource
    UserService userService;
    @Resource
    ClubService clubService;

    @Test
    void contextLoads() {
        System.out.println(matchRepository.findAll());
    }

}
