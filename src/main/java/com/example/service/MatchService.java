package com.example.service;

import com.example.entity.Matchtable;

import java.util.List;

public interface MatchService {

    List<Matchtable> selectAllMatch();

    boolean addMatch(int hometeam, int awayteam, String starttime, int matchtime, int winteam, String videoweb);

    boolean updateMatch(int matchid, String hometeam, String awayteam, String starttime, int matchtime, String winteam, String videoweb);

    int countsum();

}
