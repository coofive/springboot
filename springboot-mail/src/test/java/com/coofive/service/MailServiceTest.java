package com.coofive.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author : coofive
 * @version : 1.0.0
 * @date : 12/4/2019 9:29 PM
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailServiceTest {
    @Resource
    private MailService mailService;

    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail();
    }

    @Test
    public void sendAttachmentMail() throws Exception {
        mailService.sendAttachmentMail();
    }

    @Test
    public void sendInlineMail() throws Exception {
        mailService.sendInlineMail();
    }
}