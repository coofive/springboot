package com.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : coofive
 * @version : 1.0.0
 * @date : 5/18/2019 10:34 AM
 */
public class LoginTest {
    @Test
    public void testLogin() {
        // 1. 获取SecurityManager工厂,此处使用ini配置文件初始化
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        // 2. 得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        // 3. 得到Subject及创建用户名/密码身份验证Token（用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("test","123");
        // 4. 登录,即身份验证
        try {
            subject.login(token);
        }catch (AuthenticationException e){
            e.printStackTrace();
        }

        Assert.assertTrue(subject.isAuthenticated());

        // 退出登录
        subject.logout();
    }
}
