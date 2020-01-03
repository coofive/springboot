package com.coofive.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 模板映射实体类
 *
 * @author coofive
 */
@Data
public class MailTemplateVO {
    /**
     * 主题
     */
    private String title;

    /**
     * 日志
     */
    private List<MailLogVO> logs;

    /**
     * 用户信息
     */
    private Map<String, Object> userInfo;

}
