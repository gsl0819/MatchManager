package com.example.service;

import com.example.entity.Player;

import java.util.List;

public interface PlayerService {

    List<Player> selectAllPlayer();

    boolean deletPlayer(int playerid);

    boolean updatePlayer(int playerid, String playername, int playerage, String clubid, String role);

    boolean addPlayer(String playername, int playerage, int playerclub, String playerrole);

}
