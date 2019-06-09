package com.util.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : coofive
 * @version : 1.0.0
 * @date : 5/4/2019 3:02 PM
 */
@Slf4j
public class BeanUtil {

    /**
     * 比较两个可能为null的对象是否相等
     *
     * @param a 对象一
     * @param b 对象二
     * @return boolean
     */
    public static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    /**
     * 比较两个可能为null的对象是否不相等
     *
     * @param a 对象一
     * @param b 对象二
     * @return boolean
     */
    public static boolean notEqual(Object a, Object b) {
        return !equal(a, b);
    }

    /**
     * 具有相同属性名称的对象转化
     *
     * @param source 源对象
     * @param target 目标对象class
     * @param <T1>   源对象类型
     * @param <T2>   目标对象类型
     * @return T2
     */
    public static <T1, T2> T2 convertClass(T1 source, Class<T2> target) {
        if (source == null) {
            return null;
        }
        try {
            return JSON.parseObject(JSON.toJSONString(source), target);
        } catch (Exception e) {
            log.error("converterClass object to object failed:{}", source, e);
        }
        return null;
    }

    /**
     * 具有相同属性名称的对象集合转化
     *
     * @param source 源对象
     * @param target 目标对象class
     * @param <T1>   源对象类型
     * @param <T2>   目标对象类型
     * @return List<T2>
     */
    public static <T1, T2> List<T2> convertClass(List<T1> source, Class<T2> target) {
        if (source == null || source.size() == 0) {
            return null;
        }
        try {
            return JSON.parseArray(JSON.toJSONString(source), target);
        } catch (Exception e) {
            log.error("converterClass array to array failed:{}", source, e);
        }
        return null;
    }

    /**
     * 具有相同属性名称的对象转化，并过滤属性
     *
     * @param source      源对象
     * @param target      目标对象class
     * @param filterNames 过滤属性
     * @param <T1>        源对象类型
     * @param <T2>        目标对象类型
     * @return T2
     */
    public static <T1, T2> T2 convertClass(T1 source, Class<T2> target, String... filterNames) {
        if (filterNames == null || filterNames.length == 0) {
            return convertClass(source, target);
        }
        try {
            // 过滤属性
            return JSON.parseObject(JSON.toJSONString(source, new ConvertPropertyFilter(filterNames)), target);
        } catch (Exception e) {
            log.error("converterClass object to object failed:{}", source, e);
        }
        return null;
    }

    /**
     * 浅拷贝
     * 复制对象属性到另一个对象
     *
     * @param source 源对象
     * @param target 目标对象class
     * @param <T>    指定类型对象
     * @return T
     */
    public static <T> T copyProperties(Object source, Class<T> target) {
        try {
            T t = target.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            log.error("copy object properties failed", e);
        }
        return null;
    }
}

/**
 * 属性过滤filter
 */
@Slf4j
class ConvertPropertyFilter implements PropertyFilter {
    private List<String> filterNameList = new ArrayList<>();

    public ConvertPropertyFilter(String... filterNames) {
        if (filterNames == null || filterNames.length == 0) {
            log.info("init filterNames is empty.");
            return;
        }

        log.info("filterNames is:{}", Arrays.toString(filterNames));
        for (int i = 0; i < filterNames.length; i++) {
            String filterName = filterNames[i];
            if (filterName == null || filterName.length() == 0) {
                log.info("filterNames[{}] is empty.", i);
                continue;
            }
            filterNameList.add(filterName);
        }
    }

    @Override
    public boolean apply(Object object, String name, Object value) {
        return !filterNameList.contains(name);
    }
}