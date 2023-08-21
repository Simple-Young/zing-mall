package com.yeeph.auth.service.impl;

import com.yeeph.auth.service.IMailService;
import com.yeeph.auth.utils.MailFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;

@Slf4j
@Service
public class MailServiceImpl implements IMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public boolean sendMail(String from, String to, String subject, String text) {
        try {
            SimpleMailMessage mailMessage = MailFactory.createSimpleMailMessage(from,to,subject,text);
            mailSender.send(mailMessage);
            return true;
        }catch (Exception mailException){
            log.error(mailException.getMessage());
        }
        return false;
    }

    @Override
    public boolean sendMail(InputStream inputStream) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage(inputStream);
            mailSender.send(mimeMessage);
            return true;
        }catch (Exception mailException){
            log.error(mailException.getMessage());
        }
        return false;
    }


}
