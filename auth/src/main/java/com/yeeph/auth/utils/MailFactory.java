package com.yeeph.auth.utils;

import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public class MailFactory {

    public static SimpleMailMessage createSimpleMailMessage(String from, String to, String subject, String text){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailMessage.setSentDate(new Date());
        return mailMessage;
    }

}
