package com.example.repo;

import com.example.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {
    @Override
    List<Club> findAll();

    @Transactional
    int deleteClubByClubid(int clubid);
}
