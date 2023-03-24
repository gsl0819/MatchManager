package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/addclub")
    public String addClub() {
        return "addclub";
    }

    @RequestMapping("/addplayer")
    public String addpalyer() {
        return "addplayer";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/club")
    public String club() {
        return "club";
    }

    @GetMapping("/player")
    public String player() {
        return "player";
    }

    @GetMapping("/showclub")
    public String showclub() {
        return "showclub";
    }

    @GetMapping("/showplayer")
    public String showplayer() {
        return "showplayer";
    }

}
