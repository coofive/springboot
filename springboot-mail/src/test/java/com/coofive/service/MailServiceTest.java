package com.coofive.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 *
 * @author : coofive
 * @version : 1.0.0
 * @date : 12/4/2019 9:29 PM
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailServiceTest {
    @Resource
    private SendMailService sendMailService;

    @Test
    public void sendSimpleMail() {
        sendMailService.sendSimpleMail();
    }

    @Test
    public void sendAttachmentMail() throws Exception {
        sendMailService.sendAttachmentMail();
    }

    @Test
    public void sendInlineMail() throws Exception {
        sendMailService.sendInlineMail();
    }
}