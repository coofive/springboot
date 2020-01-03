package com.coofive.service.impl;

import com.coofive.entity.MailLogVO;
import com.coofive.entity.MailTemplateVO;
import com.coofive.service.SendMailService;
import com.coofive.service.template.MessageContentBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author coofive
 */
@Slf4j
@Service
public class SendMailServiceImpl implements SendMailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MessageContentBuilder messageBuilder;

    private final static String SEND_FROM = "xxx@163.com";
    private final static String SEND_TO = "xxx@qq.com";

    /**
     * 简单发送邮件
     */
    @Override
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
    @Override
    public void sendAttachmentMail() throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(SEND_FROM);
        helper.setTo(SEND_TO);
        helper.setSubject("确认附件内容");
        helper.setText("确认附件的内容");

        File file = ResourceUtils.getFile("classpath:test.jpg");
        helper.addAttachment("附件-1.jpg", file);
        helper.addAttachment("附件-2.jpg", file);

        javaMailSender.send(mimeMessage);
    }

    /**
     * 发送嵌入静态资源的邮件
     */
    @Override
    public void sendInlineMail() throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(SEND_FROM);
        helper.setTo(SEND_TO);
        helper.setSubject("确认嵌入静态资源");
        helper.setText("<html><body>静态资源<img src=\"cid:test\" ></body></html>", true);

        File file = ResourceUtils.getFile("classpath:test.jpg");
        helper.addAttachment("附件-1.jpg", file);
        helper.addInline("test", file);

        javaMailSender.send(mimeMessage);
    }

    /**
     * 发送html模板邮件
     *
     * @throws Exception e
     */
    @Override
    public void sendTemplateMail() throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(SEND_FROM);
        helper.setTo(SEND_TO);
        Map<String, Object> beanMap = buildMailMap();
        String htmlText = messageBuilder.buildMessage("test", beanMap);
        helper.setText(htmlText, true);
    }

    /**
     * 构建html映射实体类
     */
    private Map<String, Object> buildMailMap() {
        MailTemplateVO mailTemplateVO = new MailTemplateVO();
        mailTemplateVO.setTitle("HTML模板主题");
        MailLogVO mailLogVO = new MailLogVO();
        MailLogVO log = mailLogVO.setCreator("coofive").setCreateTime(new Timestamp(System.currentTimeMillis()));
        List<MailLogVO> logs = new ArrayList<>();
        logs.add(log);
        logs.add(log);
        mailTemplateVO.setLogs(logs);
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("name", "coofive");
        userInfo.put("age", 20);
        mailTemplateVO.setUserInfo(userInfo);
        Map<String, Object> beanMap = new HashMap<>();
        BeanUtils.copyProperties(mailTemplateVO, beanMap);
        return beanMap;
    }
}
