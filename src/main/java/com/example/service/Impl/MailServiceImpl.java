package com.example.service.Impl;

import com.example.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class MailServiceImpl implements MailService {
    @Resource
    JavaMailSender sender;
    @Resource
    StringRedisTemplate template;

    @Value("${spring.mail.username}")
    String from;

    @Override
    public void sendVerifyCode(String mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("【xxx网站】您的注册验证码");
        Random random = new Random();
        int code = random.nextInt(899999) + 100000;
        template.opsForValue().set("verify:code:" + mail, code + "", 3, TimeUnit.MINUTES);
        message.setText("您的注册验证码为：" + code + " （三分钟内有效，如果不是本人操作请忽略）");
        message.setTo(mail);
        message.setFrom(from);
        sender.send(message);
    }

    @Override
    public boolean doVerify(String mail, String code) {
        String s = template.opsForValue().get("verify:code:" + mail);
        if (s == null) return false;
        if (!s.equals(code)) return false;
        template.delete("verify:code:" + mail);
        return true;
    }
}
