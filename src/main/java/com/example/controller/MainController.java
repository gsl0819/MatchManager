package com.example.controller;

import com.example.entity.User;
import com.example.entity.resp.RestBean;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;


@Controller
public class MainController {
    @Resource
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @RestController
    public class BootController {
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

    }

}
