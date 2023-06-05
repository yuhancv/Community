package com.hancoder.community.util;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component  //通用的Bean
public class MailClient {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(MailClient.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    //发给谁 , 邮件的主题 ， 邮件的内容
    public void sendMail(String to, String subject, String content){
        MimeMessage message = mailSender.createMimeMessage();
    }




}



