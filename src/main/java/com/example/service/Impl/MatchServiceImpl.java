package com.example.service.Impl;

import com.example.entity.Club;
import com.example.entity.Matchtable;
import com.example.entity.Player;
import com.example.repo.ClubRepository;
import com.example.repo.MatchRepository;
import com.example.service.ClubService;
import com.example.service.MatchService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class MatchServiceImpl implements MatchService {

    @Resource
    MatchRepository matchRepository;
    @Resource
    ClubRepository clubRepository;

    @Override
    public List<Matchtable> selectAllMatch() {
        return matchRepository.findAll();
    }

    @Override
    public boolean addMatch(int hometeam, int awayteam, String starttime, int matchtime, int winteam, String videoweb) {
        Matchtable matchtable = new Matchtable();
        matchtable.setHometeam(hometeam);
        matchtable.setAwayteam(awayteam);
        matchtable.setStarttime(starttime);
        matchtable.setMatchtime(matchtime);
        matchtable.setWinteam(winteam);
        matchtable.setVideoweb(videoweb);
        try {
            matchRepository.save(matchtable);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateMatch(int matchid, String hometeam, String awayteam, String starttime, int matchtime, String winteam, String videoweb) {
        int home = clubRepository.findClubByClubname(hometeam).getClubid();
        int away = clubRepository.findClubByClubname(awayteam).getClubid();
        int win = clubRepository.findClubByClubname(winteam).getClubid();
        Optional<Matchtable> op = matchRepository.findById(matchid);
        AtomicReference<Matchtable> matchtest = new AtomicReference<>(new Matchtable());
        op.ifPresent(match -> {
            match.setHometeam(home);
            match.setAwayteam(away);
            match.setStarttime(starttime);
            match.setMatchtime(matchtime);
            match.setWinteam(win);
            match.setVideoweb(videoweb);
            matchtest.set(matchRepository.save(match));
        });
        if (matchtest == null) return false;
        else return true;
    }

    @Override
    public int countsum() {
        return matchRepository.countMatchtablesByMatchidNotNull();
    }
}
