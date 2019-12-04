package com.coofive.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author : coofive
 * @version : 1.0.0
 * @date : 12/4/2019 9:26 PM
 */
@Slf4j
@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private final static String SEND_FROM = "xxx@163.com";
    private final static String SEND_TO = "xxx@qq.com";

    /**
     * 简单发送邮件
     */
    public void sendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(SEND_FROM);
        message.setTo(SEND_TO);
        message.setSubject("确认订单内容");
        message.setText("您已购买春秋航空的机票！");
        javaMailSender.send(message);
    }

    /**
     * 发送附件邮件
     */
    public void sendAttachmentMail() throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(SEND_FROM);
        helper.setTo(SEND_TO);
        helper.setSubject("确认附件内容");
        helper.setText("确认附件的内容");

        File file = ResourceUtils.getFile("classpath:test.jpg");
        helper.addAttachment("附件-1.jpg",file);
        helper.addAttachment("附件-2.jpg",file);

        javaMailSender.send(mimeMessage);
    }

    /**
     * 发送嵌入静态资源的邮件
     */
    public void sendInlineMail() throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(SEND_FROM);
        helper.setTo(SEND_TO);
        helper.setSubject("确认嵌入静态资源");
        helper.setText("<html><body>静态资源<img src=\"cid:test\" ></body></html>", true);

        File file = ResourceUtils.getFile("classpath:test.jpg");
        helper.addAttachment("附件-1.jpg",file);
        helper.addInline("test", file);

        javaMailSender.send(mimeMessage);
    }
}
