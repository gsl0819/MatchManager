package com.example.service.Impl;

import com.example.entity.Club;
import com.example.entity.User;
import com.example.repo.ClubRepository;
import com.example.service.ClubService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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

    @Override
    public boolean updateClub(int clubid, String clubname) {
        Optional<Club> op = clubRepository.findById(clubid);
        AtomicReference<Club> clubtest = new AtomicReference<>(new Club());
        op.ifPresent(club -> {
            club.setClubname(clubname);
            clubtest.set(clubRepository.save(club));
        });
        if (clubtest == null) return false;
        else return true;
    }

    @Override
    public boolean addClub(String clubname) {
        Club club = new Club();
        club.setClubname(clubname);
        try {
            clubRepository.save(club);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
