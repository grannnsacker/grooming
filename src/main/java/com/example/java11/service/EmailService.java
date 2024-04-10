package com.example.java11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendMessage(String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("testjavasendler@gmail.com");
        message.setTo("testjavasendler@gmail.com");
        message.setSubject("Info message");
        message.setText(text);

        javaMailSender.send(message);
    }
}