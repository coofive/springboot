package com.util.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author wenwu.liu.o
 */
@Slf4j
public class FastJsonUtil {
    /*==============================object to json=======================================*/

    /**
     * 将obj序列化成jsonString
     *
     * @param obj 对象
     * @return String
     */
    public static String objectToJson(Object obj) {
        try {
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            log.error("obj to json failed.", e);
        }
        return null;
    }

    /*==============================json to object=======================================*/

    /**
     * 将jsonString 反序列化成object
     *
     * @param jsonString json字符串
     * @param tClass     指定类
     * @param <T>        指定泛型
     * @return T
     */
    public static <T> T jsonToObject(String jsonString, Class<T> tClass) {
        try {
            return JSON.parseObject(jsonString, tClass);
        } catch (Exception e) {
            log.error("parse json to object failed:{}", jsonString, e);
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
        try {
            return JSON.parseObject(jsonString, type);
        } catch (Exception e) {
            log.error("parse json to object failed:{}", jsonString, e);
        }
        return null;
    }

    /**
     * 将jsonString 反序列化成指定对象的List
     *
     * @param jsonString json字符串
     * @param tClass     指定类型
     * @param <T>        指定泛型
     * @return T
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> tClass) {
        try {
            return JSON.parseArray(jsonString, tClass);
        } catch (Exception e) {
            log.error("parse json to list failed:{}", jsonString, e);
        }
        return null;
    }

    /**
     * 将jsonString 反序列化成指定Map
     *
     * @param jsonString json字符串
     * @return T
     */
    public static Map jsonToMap(String jsonString) {
        try {
            return JSON.parseObject(jsonString, Map.class);
        } catch (Exception e) {
            log.error("parse json to map failed:{}", jsonString, e);
        }
        return null;
    }

    /**
     * 将jsonString 反序列化成指定String泛型Map
     *
     * @param jsonString json字符串
     * @return T
     */
    public static Map<String, Object> jsonToStrMap(String jsonString) {
        try {
            return JSON.parseObject(jsonString, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            log.error("parse json to string map failed:{}", jsonString, e);
        }
        return null;
    }

    /*==============================json to object by key=======================================*/

    /**
     * 将jsonString 取出对应key的Object
     *
     * @param jsonString json字符串
     * @param key        对应字段key
     * @param clazz      指定class
     * @param <T>        指定泛型
     * @return T
     */
    public static <T> T getAsObject(String jsonString, String key, Class<T> clazz) {
        try {
            return JSON.parseObject(jsonString).getObject(key, clazz);
        } catch (Exception e) {
            log.error("parse json to object failed:{}", jsonString, e);
        }
        return null;
    }

    /**
     * 将jsonString 取出对应key的Object
     *
     * @param jsonString json字符串
     * @param key        对应字段key
     * @param type       指定类型
     * @param <T>        指定泛型
     * @return T
     */
    public static <T> T getAsObject(String jsonString, String key, TypeReference<T> type) {
        try {
            return JSON.parseObject(jsonString).getObject(key, type);
        } catch (Exception e) {
            log.error("parse json to object failed:{}", jsonString, e);
        }
        return null;
    }

    /**
     * 将jsonString 取出对应key的List
     *
     * @param jsonString json字符串
     * @param key        对应字段key
     * @param clazz      指定类型
     * @param <T>        指定泛型
     * @return List
     */
    public static <T> List<T> getAsList(String jsonString, String key, Class<T> clazz) {
        try {
            return JSON.parseObject(jsonString).getObject(key, new TypeReference<List<T>>() {
            });
        } catch (Exception e) {
            log.error("parse json to list failed:{}", jsonString, e);
        }
        return null;
    }

    /**
     * 将jsonString 取出对应key的Map
     *
     * @param jsonString json字符串
     * @param key        对应字段key
     * @return Map
     */
    public static Map getAsMap(String jsonString, String key) {
        try {
            return JSON.parseObject(jsonString).getObject(key, Map.class);
        } catch (Exception e) {
            log.error("parse json to map failed:{}", jsonString, e);
        }
        return null;
    }

    /**
     * 将jsonString 取出对应key的String Map
     *
     * @param jsonString json字符串
     * @param key        对应字段key
     * @return Map
     */
    public static Map<String, Object> getAsStrMap(String jsonString, String key) {
        try {
            return JSON.parseObject(jsonString).getObject(key, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            log.error("parse json to map failed:{}", jsonString, e);
        }
        return null;
    }

    /**
     * 将jsonString 取出对应key的String
     *
     * @param jsonString json字符串
     * @param key        对应字段key
     * @return String
     */
    public static String getAsString(String jsonString, String key) {
        try {
            return JSON.parseObject(jsonString).getString(key);
        } catch (Exception e) {
            log.error("parse json to string failed:{}", jsonString, e);
        }
        return null;
    }

    /**
     * 将jsonString 取出对应key的Integer
     *
     * @param jsonString json字符串
     * @param key        对应字段key
     * @return Integer
     */
    public static Integer getAsInteger(String jsonString, String key) {
        try {
            return JSON.parseObject(jsonString).getInteger(key);
        } catch (Exception e) {
            log.error("parse json to integer failed:{}", jsonString, e);
        }
        return null;
    }

    /**
     * 将jsonString 取出对应key的Long
     *
     * @param jsonString json字符串
     * @param key        对应字段key
     * @return Long
     */
    public static Long getAsLong(String jsonString, String key) {
        try {
            return JSON.parseObject(jsonString).getLong(key);
        } catch (Exception e) {
            log.error("parse json to long failed:{}", jsonString, e);
        }
        return null;
    }

    /**
     * 将jsonString 取出对应key的Double
     *
     * @param jsonString json字符串
     * @param key        对应字段key
     * @return Double
     */
    public static Double getAsDouble(String jsonString, String key) {
        try {
            return JSON.parseObject(jsonString).getDouble(key);
        } catch (Exception e) {
            log.error("parse json to double failed:{}", jsonString, e);
        }
        return null;
    }

    /*==============================jsonMap key to object=======================================*/

    /**
     * 将jsonMap 通过key取出Object
     *
     * @param map   map
     * @param key   字段key
     * @param clazz 类型
     * @param <T>   指定泛型
     * @return T
     */
    public static <T> T getAsObjectFromMap(Map map, String key, Class<T> clazz) {
        try {
            return JSONObject.toJavaObject((JSON) map.get(key), clazz);
        } catch (Exception e) {
            log.error("map key to object failed:{}", map, e);
        }
        return null;
    }

    /**
     * 将jsonMap 通过key取出Object
     *
     * @param map  map
     * @param key  字段key
     * @param type 类型
     * @param <T>  指定泛型
     * @return T
     */
    public static <T> T getAsObjectFromMap(Map map, String key, TypeReference<T> type) {
        try {
            JSON json = (JSON) map.get(key);
            return JSON.parseObject(JSON.toJSONString(json), type);
        } catch (Exception e) {
            log.error("map key to object failed:{}", map, e);
        }
        return null;
    }

    /**
     * 将jsonMap 通过key取出List
     *
     * @param map   map
     * @param key   字段key
     * @param clazz 类型
     * @param <T>   指定泛型
     * @return List
     */
    public static <T> List<T> getAsListFromMap(Map map, String key, Class<T> clazz) {
        try {
            JSON json = (JSON) map.get(key);
            return JSON.parseArray(JSON.toJSONString(json), clazz);
        } catch (Exception e) {
            log.error("map key to list failed:{}", map, e);
        }
        return null;
    }
}
