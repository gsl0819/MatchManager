package com.example.controller;

import com.example.entity.Club;
import com.example.entity.User;
import com.example.entity.resp.RestBean;
import com.example.service.ClubService;
import com.example.service.MatchService;
import com.example.service.PlayerService;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@RestController
public class AdminController {
    @Resource
    UserService userService;
    @Resource
    ClubService clubService;
    @Resource
    PlayerService playerService;
    @Resource
    MatchService matchService;

    @GetMapping(value = "/api/auth/logout-success")
    public RestBean<Void> logoutSuccess() {
        return new RestBean<>(200, "退出成功！");
    }

    @GetMapping(value = "/manageUser")
    public RestBean<User> manageUser() {
        return new RestBean<>(200, "", userService.selectAllUser());
    }


    @GetMapping(value = "/deletUser")
    public RestBean<Void> deletUser(int userid) {
        if (userService.deletUser(userid))
            return new RestBean<>(200, "删除成功");
        else return new RestBean<>(400, "删除失败！");
    }

    @GetMapping(value = "/updatetUser")
    public RestBean<Void> updatetUser(int userid, String username) {
        if (userService.updateUser(userid, username))
            return new RestBean<>(200, "修改成功");
        else return new RestBean<>(400, "删除失败！");
    }

    @GetMapping(value = "/findRoleByUsername")
    public RestBean<User> findRoleByUsername(String username) {
        String role = userService.getRoleByUsername(username);
        return new RestBean<>(200, role);
    }

    @GetMapping(value = "/manageClub")
    public RestBean<Club> manageClub() {
        return new RestBean<>(200, "", clubService.selectAllClub());
    }

    @GetMapping(value = "/managePlayer")
    public List<RestBean> managePlayer() {
        List<RestBean> list = new LinkedList<>();
        list.add(new RestBean<>(200, "", playerService.selectAllPlayer()));
        list.add(new RestBean<>(200, "", clubService.selectAllClub()));
        return list;
    }

    @GetMapping(value = "/manageMatch")
    public List<RestBean> manageMatch() {
        List<RestBean> list = new LinkedList<>();
        list.add(new RestBean<>(200, "", matchService.selectAllMatch()));
        list.add(new RestBean<>(200, "", clubService.selectAllClub()));
        return list;
    }

    @GetMapping(value = "/deletClub")
    public RestBean<Void> deletClub(int clubid) {
        if (clubService.deletClub(clubid))
            return new RestBean<>(200, "删除成功");
        else return new RestBean<>(400, "删除失败！");
    }

    @GetMapping(value = "/deletPlayer")
    public RestBean<Void> deletPlayer(int playerid) {
        if (playerService.deletPlayer(playerid))
            return new RestBean<>(200, "删除成功");
        else return new RestBean<>(400, "删除失败！");
    }

    @GetMapping(value = "/updatetClub")
    public RestBean<Void> updatetClub(int clubid, String clubname) {
        if (clubService.updateClub(clubid, clubname))
            return new RestBean<>(200, "修改成功");
        else return new RestBean<>(400, "修改失败！");
    }

    @GetMapping(value = "/updatePlayer")
    public RestBean<Void> updatePlayer(int playerid,
                                       String playername,
                                       int playerage,
                                       String clubname,
                                       String role) {
        if (playerService.updatePlayer(playerid, playername, playerage, clubname, role))
            return new RestBean<>(200, "修改成功");
        else return new RestBean<>(400, "修改失败！");
    }

    @GetMapping(value = "/updateMatch")
    public RestBean<Void> updateMatch(int matchid,
                                      String hometeam,
                                      String awayteam,
                                      String starttime,
                                      int matchtime,
                                      String winteam,
                                      String videoweb) {
        if (matchService.updateMatch(matchid, hometeam, awayteam, starttime, matchtime, winteam, videoweb))
            return new RestBean<>(200, "修改成功");
        else return new RestBean<>(400, "修改失败！");
    }

    @GetMapping(value = "/addclubinjs")
    public RestBean<Void> addClub(String clubname) {
        if (clubService.addClub(clubname))
            return new RestBean<>(200, "添加成功");
        else return new RestBean<>(400, "战队名已存在！添加失败！");
    }

    @GetMapping(value = "/addplayerinjs")
    public RestBean<Void> addplayer(String playername,
                                    int playerage,
                                    int playerclub,
                                    String playerrole) {
        if (playerService.addPlayer(playername, playerage, playerclub, playerrole))
            return new RestBean<>(200, "添加成功");
        else return new RestBean<>(400, "添加失败！");

    }

    @GetMapping(value = "/addmatchinjs")
    public RestBean<Void> addmatch(int hometeam,
                                   int awayteam,
                                   String starttime,
                                   int matchtime,
                                   int winteam,
                                   String videoweb) {
        if (matchService.addMatch(hometeam, awayteam, starttime, matchtime, winteam, videoweb))
            return new RestBean<>(200, "添加成功");
        else return new RestBean<>(400, "添加失败！");

    }

    @GetMapping(value = "/countsum")
    public List<RestBean> countsum() {
        int usercount = userService.countsum();
        int clubcount = clubService.countsum();
        int playercount = playerService.countsum();
        int matchcount = matchService.countsum();
        List<RestBean> list = new LinkedList<>();
        list.add(new RestBean<>(200, usercount + ""));
        list.add(new RestBean<>(200, clubcount + ""));
        list.add(new RestBean<>(200, playercount + ""));
        list.add(new RestBean<>(200, matchcount + ""));
        return list;
    }

}
