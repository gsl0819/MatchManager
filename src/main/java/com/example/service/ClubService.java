package com.example.service;

import com.example.entity.Club;

import java.util.List;

public interface ClubService {
    List<Club> selectAllClub();

    boolean deletClub(int clubid);

    boolean updateClub(int clubid, String clubname);

    boolean addClub(String clubname);

    int countsum();
}

