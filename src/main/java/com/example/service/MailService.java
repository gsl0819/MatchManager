package com.example.service;

public interface MailService {
    void sendVerifyCode(String mail);

    boolean doVerify(String mail, String code);
}
