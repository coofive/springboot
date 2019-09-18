package com.util.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

/**
 * @author wenwu.liu.o
 */
@Slf4j
public final class JacksonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 全部字段序列化
        // 对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /*==============================object to json=======================================*/

    /**
     * 将obj序列化成jsonString
     *
     * @param obj 对象
     * @return String
     */
    public static String objectToJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("obj to json failed, obj:{}", obj, e);
        }
        return null;
    }

    /*==============================json to object=======================================*/

    /**
     * 将jsonString 反序列化成object
     *
     * @param jsonString json字符串
     * @param clazz      指定类
     * @param <T>        指定泛型
     * @return T
     */
    public static <T> T jsonToObject(String jsonString, Class<T> clazz) {
        if (jsonString == null || jsonString.length() == 0) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) jsonString : objectMapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            log.error("parse json to object failed, jsonString:{}", jsonString, e);
        }
        return null;
    }

    /**
     * 将jsonString 反序列化成object
     *
     * @param jsonString json字符串
     * @param type       指定类型
     * @param <T>        指定泛型
     * @return T
     */
    public static <T> T jsonToObject(String jsonString, TypeReference<T> type) {
        if (jsonString == null || jsonString.length() == 0 || type == null) {
            return null;
        }
        try {
            return type.getType().equals(String.class) ? (T) jsonString : objectMapper.readValue(jsonString, type);
        } catch (Exception e) {
            log.error("parse json to object failed:{}", jsonString, e);
        }
        return null;
    }

    /**
     * 转化对象
     *
     * @param source 源对象
     * @param type   类型
     * @param <T1>   源对象类型
     * @param <T2>   目标对象类型
     * @return 目标对象
     */
    public static <T1, T2> T2 convertClass(T1 source, TypeReference<T2> type) {
        if (source == null || type == null) {
            return null;
        }
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(source), type);
        } catch (Exception e) {
            log.error("convert object to object failed:{}", source, e);
        }
        return null;
    }
}
