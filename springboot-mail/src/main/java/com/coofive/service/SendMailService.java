package com.coofive.service;

/**
 * 发送邮件
 *
 * @author : coofive
 * @version : 1.0.0
 * @date : 12/4/2019 9:26 PM
 */
public interface SendMailService {

    /**
     * 简单发送邮件
     */
    void sendSimpleMail();

    /**
     * 发送附件邮件
     *
     * @throws Exception e
     */
    void sendAttachmentMail() throws Exception;

    /**
     * 发送嵌入静态资源的邮件
     *
     * @throws Exception e
     */
    void sendInlineMail() throws Exception;

    /**
     * 发送html模板邮件
     *
     * @throws Exception e
     */
    void sendTemplateMail() throws Exception;
}
