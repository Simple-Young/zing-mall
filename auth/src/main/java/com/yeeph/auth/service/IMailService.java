package com.yeeph.auth.service;

import java.io.InputStream;

public interface IMailService {


    boolean sendMail(String from, String to, String subject, String text);

    boolean sendMail(InputStream inputStream);

}
