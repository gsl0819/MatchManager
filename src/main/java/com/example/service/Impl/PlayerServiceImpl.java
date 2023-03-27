package com.example.service.Impl;

import com.example.entity.Club;
import com.example.entity.Player;
import com.example.entity.User;
import com.example.repo.ClubRepository;
import com.example.repo.PlayerRepository;
import com.example.service.PlayerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Resource
    PlayerRepository playerRepository;
    @Resource
    ClubRepository clubRepository;

    @Override
    public List<Player> selectAllPlayer() {
        return playerRepository.findAll();
    }

    @Override
    public boolean deletPlayer(int playerid) {
        playerRepository.deletePlayerByPlayerid(playerid);
        return true;
    }

    @Override
    public boolean updatePlayer(int playerid, String playername, int playerage, String clubname, String role) {
        Club club = clubRepository.findClubByClubname(clubname);
        int clubid = club.getClubid();
        Optional<Player> op = playerRepository.findById(playerid);
        AtomicReference<Player> playertest = new AtomicReference<>(new Player());
        op.ifPresent(player -> {
            player.setPlayername(playername);
            player.setPlayerage(playerage);
            player.setClubid(clubid);
            player.setRole(role);
            playertest.set(playerRepository.save(player));
        });
        if (playertest == null) return false;
        else return true;
    }

    @Override
    public boolean addPlayer(String playername, int playerage, int playerclub, String playerrole) {
        Player player = new Player();
        player.setPlayername(playername);
        player.setPlayerage(playerage);
        player.setClubid(playerclub);
        player.setRole(playerrole);
        try {
            playerRepository.save(player);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int countsum() {
        return playerRepository.countPlayersByClubidNotNull();
    }
}
