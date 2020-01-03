package com.coofive.service.impl;

import com.coofive.service.ReceiveMailService;
import com.coofive.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.mail.*;
import java.security.Security;
import java.util.Properties;

/**
 * @author wenwu.liu.o
 */
@Slf4j
@Service
public class ReceiveMailServiceImpl implements ReceiveMailService {


    /**
     * 触发接收服务器邮件
     */
    @Override
    public void receiveMails() {
        log.info(">>>>>>>>>>开始接收邮件>>>>>>>>>>");
        Store store = null;
        try {
            // 获取store
            store = getNewOne();
            receiveMails(store);
        } catch (MessagingException e) {
            log.error("获取INDEX收件箱失败", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 释放store
            release(store);
        }
        log.info(">>>>>>>>>>结束接收邮件>>>>>>>>>>>");
    }

    private void receiveMails(Store store) throws MessagingException {
        // 默认获取INDEX
        Folder folder = store.getDefaultFolder().getFolder("INBOX");
        // 读写方式打开收件箱
        folder.open(Folder.READ_WRITE);
        // 获取收件箱中未读邮件列表, 已读的邮件不作处理，只处理未读的
        Message[] search = folder.getMessages(folder.getMessageCount() - folder.getUnreadMessageCount() + 1, folder.getMessageCount());
        /*Message[] search = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));*/
        if (search.length == 0) {
            log.info("获取未读邮件，无未读邮件");
            return;
        }
        log.info("获取未读邮件，[{}]封未读邮件", search.length);
        for (Message message : search) {
            // 解析消息
            if (!parseMessage(message)) {
                message.setFlag(Flags.Flag.SEEN, false);
                log.info("设置当前邮件为未读, 主题:{}", message.getSubject());
            } else {
                message.setFlag(Flags.Flag.SEEN, true);
                log.info("设置当前邮件为已读, 主题:{}", message.getSubject());
            }
        }
    }

    private boolean parseMessage(Message message) {

        return false;
    }

    /**
     * 解析邮件文本回复内容
     * 默认截取第一条回复内容
     *
     * @param message 邮件信息
     * @return 文本回复内容
     */
    private String parseMailText(Message message) throws Exception {
        // 解析得到html内容
        StringBuffer content = new StringBuffer();
        getMailTextContent(message, content);
        // 解析html内容
        Document doc = Jsoup.parse(content.toString());
        if (doc == null) {
            return null;
        }
        return parseElements(new StringBuilder(), doc.select("p.MsoNormal"), "发件人: ");
    }

    /**
     * 遍历解析指定元素文本内容
     * 指定包含内容结束递归
     *
     * @param sb     拼接字符串
     * @param select 选择的html元素
     * @return 文本内容
     */
    private String parseElements(StringBuilder sb, Elements select, String rule) {
        String text = select.first().text();
        if (BeanUtil.isNotEmpty(text) && text.contains(rule)) {
            return sb.toString();
        }
        if (BeanUtil.isNotEmpty(text)) {
            sb.append(text.trim());
        }
        return parseElements(sb, select.next(), rule);
    }

    /**
     * 获得邮件文本内容,包含html文本内容
     *
     * @param part    邮件体
     * @param content 存储邮件文本内容的字符串
     */
    public static void getMailTextContent(Part part, StringBuffer content) throws Exception {
        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
        if (part.isMimeType("text/*") && !isContainTextAttach) {
            content.append(part.getContent().toString());
        } else if (part.isMimeType("message/rfc822")) {
            getMailTextContent((Part) part.getContent(), content);
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                getMailTextContent(bodyPart, content);
            }
        }
    }

    /**
     * 获取新的store
     */
    private Store getNewOne() throws MessagingException {
        // 设置SSL连接、邮件环境
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        // 设置账户配置
        Properties properties = System.getProperties();
        properties.setProperty("mail.imap.host", "partner.outlook.cn");
        properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback", "false");
        properties.setProperty("mail.imap.port", "993");
        properties.setProperty("mail.imap.socketFactory.port", "993");
        properties.setProperty("mail.imap.auth", "true");

        Session session = Session.getInstance(properties);
        Store store = session.getStore("imap");
        store.connect("username", "password");
        return store;
    }

    /**
     * 释放store
     */
    private void release(Store store) {
        if (store == null) {
            return;
        }
        try {
            store.close();
        } catch (MessagingException e) {
            log.warn("释放邮件STORE失败", e);
        } finally {
            store = null;
        }
    }
}
