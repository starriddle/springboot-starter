package com.starriddle.starter.springboot.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息发送服务
 *
 * @author CYL
 * @date 2018-12-01
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @RequestMapping("/sendEmail")
    @ResponseBody
    public boolean sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(mailFrom);
        msg.setBcc();
        msg.setTo(mailFrom);
        msg.setSubject("测试springboot发邮件");
        msg.setText("本邮件由 spring-boot-starter-mail 配置并发送");
        try {
            javaMailSender.send(msg);
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
