package com.example.repo;

import com.example.entity.Matchtable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Matchtable, Integer> {
    @Override
    List<Matchtable> findAll();

    Matchtable findMatchtableByMatchid(int matchid);
}
