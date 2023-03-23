package com.example.repo;

import com.example.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Override
    List<Player> findAll();

    @Transactional
    int deletePlayerByPlayerid(int playerid);
}
