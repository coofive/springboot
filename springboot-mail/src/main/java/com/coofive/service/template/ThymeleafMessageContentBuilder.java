package com.coofive.service.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;
import java.util.Objects;

/**
 * @author wenwu.liu.o
 */
@Service
public class ThymeleafMessageContentBuilder implements MessageContentBuilder {
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 构建模板信息
     *
     * @param templateName 模板名称
     * @param data         需要映射的数据
     * @return 构建好的信息
     */
    @Override
    public String buildMessage(String templateName, Map<String, Object> data) {
        if (Objects.isNull(data)) {
            return null;
        }
        Context context = new Context();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        return templateEngine.process(templateName, context);
    }
}
