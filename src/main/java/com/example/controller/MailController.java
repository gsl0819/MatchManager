package com.example.controller;

import com.example.entity.resp.RestBean;
import com.example.service.MailService;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/auth")
public class MailController {
    @Resource
    MailService mailService;
    @Resource
    UserService userService;

    @GetMapping("/verify-code")
    public RestBean<Void> verifyCode(@RequestParam("email") String email) {
        try {
            mailService.sendVerifyCode(email);
            return new RestBean<>(200, "邮件发送成功！");
        } catch (Exception e) {
            return new RestBean<>(500, "邮件发送失败！");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public RestBean<Void> register(
            String email,
            String verify,
            String password) {
        if (mailService.doVerify(email, verify)) {
            if (userService.creatUser(email, password, "普通用户")){
                return new RestBean<>(200, "注册成功！");
            }
            else {
                return new RestBean<>(402, "注册失败！账号已被注册！");
            }
        } else {
            return new RestBean<>(403, "注册失败！验证码填写错误");
        }
    }
}
