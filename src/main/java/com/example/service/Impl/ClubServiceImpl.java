package com.example.service.Impl;

import com.example.entity.Club;
import com.example.repo.ClubRepository;
import com.example.service.ClubService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {
    @Resource
    ClubRepository clubRepository;

    @Override
    public List<Club> selectAllClub() {
        return clubRepository.findAll();
    }

    @Override
    public boolean deletClub(int clubid) {
        return clubRepository.deleteClubByClubid(clubid) == 1;
    }
}
