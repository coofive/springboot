package com.coofive.service.template;

import java.util.Map;

/**
 * 模板信息构建
 *
 * @author wenwu.liu.o
 */
public interface MessageContentBuilder {
    /**
     * 构建模板信息
     *
     * @param templateName 模板名称
     * @param data         需要映射的数据
     * @return 构建好的html信息
     */
    String buildMessage(String templateName, Map<String, Object> data);
}
