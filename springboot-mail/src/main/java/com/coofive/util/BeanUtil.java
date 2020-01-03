package com.coofive.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.PropertyFilter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * @author wenwu.liu.o
 */
public class BeanUtil {
    private static final Logger log = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 校验集合是否为空
     *
     * @param coll 入参
     * @return boolean
     */
    public static boolean isEmpty(Collection<?> coll) {
        return (coll == null || coll.isEmpty());
    }

    /**
     * 校验集合是否不为空
     *
     * @param coll 入参
     * @return boolean
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * 判断Map是否为空
     *
     * @param map 入参
     * @return boolean
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 判断Map是否不为空
     *
     * @param map 入参
     * @return boolean
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 判断对象数组是否为空
     *
     * @param objects 数组
     * @return boolean
     */
    public static boolean isEmpty(Object[] objects) {
        return (objects == null || objects.length == 0);
    }

    /**
     * 判断对象数组是否不为空
     *
     * @param objects 数组
     * @return boolean
     */
    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    /**
     * 判断long类型数组是否为空
     *
     * @param array long类型数组
     * @return boolean
     */
    public static boolean isEmpty(long[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * 判断long类型数组是否不为空
     *
     * @param array long类型数组
     * @return boolean
     */
    public static boolean isNotEmpty(long[] array) {
        return !isEmpty(array);
    }

    /**
     * 判断int类型数组是否为空
     *
     * @param array int类型数组
     * @return boolean
     */
    public static boolean isEmpty(int[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * 判断int类型数组是否不为空
     *
     * @param array int类型数组
     * @return boolean
     */
    public static boolean isNotEmpty(int[] array) {
        return !isEmpty(array);
    }

    /**
     * 判断short类型数组是否为空
     *
     * @param array short类型数组
     * @return boolean
     */
    public static boolean isEmpty(short[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * 判断short类型数组是否不为空
     *
     * @param array short类型数组
     * @return boolean
     */
    public static boolean isNotEmpty(short[] array) {
        return !isEmpty(array);
    }

    /**
     * 判断char类型数组是否为空
     *
     * @param array char类型数组
     * @return boolean
     */
    public static boolean isEmpty(char[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * 判断char类型数组是否不为空
     *
     * @param array char类型数组
     * @return boolean
     */
    public static boolean isNotEmpty(char[] array) {
        return !isEmpty(array);
    }

    /**
     * 判断byte类型数组是否为空
     *
     * @param array byte类型数组
     * @return boolean
     */
    public static boolean isEmpty(byte[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * 判断byte类型数组是否不为空
     *
     * @param array byte类型数组
     * @return boolean
     */
    public static boolean isNotEmpty(byte[] array) {
        return !isEmpty(array);
    }

    /**
     * 判断double类型数组是否为空
     *
     * @param array double类型数组
     * @return boolean
     */
    public static boolean isEmpty(double[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * 判断double类型数组是否不为空
     *
     * @param array double类型数组
     * @return boolean
     */
    public static boolean isNotEmpty(double[] array) {
        return !isEmpty(array);
    }

    /**
     * 判断float类型数组是否为空
     *
     * @param array float类型数组
     * @return boolean
     */
    public static boolean isEmpty(float[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * 判断float类型数组是否不为空
     *
     * @param array float类型数组
     * @return boolean
     */
    public static boolean isNotEmpty(float[] array) {
        return !isEmpty(array);
    }

    /**
     * 判断boolean类型数组是否为空
     *
     * @param array boolean类型数组
     * @return boolean
     */
    public static boolean isEmpty(boolean[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * 判断boolean类型数组是否不为空
     *
     * @param array boolean类型数组
     * @return boolean
     */
    public static boolean isNotEmpty(boolean[] array) {
        return !isEmpty(array);
    }

    /**
     * 判断String 是否为空为null
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }

    /**
     * 判断String 是否不为空不为null
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    /**
     * 判断obj 是否为空为null
     *
     * @param obj 对象
     * @return boolean
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof CharSequence) {
            return isEmpty((CharSequence) obj);
        }
        if (obj instanceof Collection) {
            return isEmpty((Collection) obj);
        }
        if (obj instanceof Map) {
            return isEmpty((Map) obj);
        }
        if (obj instanceof Object[]) {
            return isEmpty((Object[]) obj);
        }
        if (obj instanceof long[]) {
            return isEmpty((long[]) obj);
        }
        if (obj instanceof int[]) {
            return isEmpty((int[]) obj);
        }
        if (obj instanceof short[]) {
            return isEmpty((short[]) obj);
        }
        if (obj instanceof char[]) {
            return isEmpty((char[]) obj);
        }
        if (obj instanceof byte[]) {
            return isEmpty((byte[]) obj);
        }
        if (obj instanceof double[]) {
            return isEmpty((double[]) obj);
        }
        if (obj instanceof float[]) {
            return isEmpty((float[]) obj);
        }
        if (obj instanceof boolean[]) {
            return isEmpty((boolean[]) obj);
        }
        return false;
    }

    /**
     * 判断obj 是否不为空不为null
     *
     * @param obj 字符串
     * @return boolean
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

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
     * @param type   目标对象类型
     * @param <T1>   源对象类型
     * @param <T2>   目标对象类型
     * @return List<T2>
     */
    public static <T1, T2> T2 convertClass(T1 source, TypeReference<T2> type) {
        if (source == null) {
            return null;
        }
        try {
            return JSON.parseObject(JSON.toJSONString(source), type);
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
            return Collections.emptyList();
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
        if (source == null) {
            return null;
        }
        try {
            T t = target.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            log.error("copy object properties failed:{}", source, e);
        }
        return null;
    }

    /**
     * map 装换为 java bean 对象
     *
     * @param map    map
     * @param target 目标对象
     * @param <T>    目标对象类型
     * @return T 目标对象实例
     */
    public static <T> T mapToBean(Map map, Class<T> target) {
        if (map == null || map.isEmpty()) {
            log.debug("map is empty");
            return null;
        }
        try {
            return JSON.parseObject(JSON.toJSONString(map), target);
        } catch (Exception e) {
            log.info("beans to maps filter null value failed:{}, error:{}", map, e.getMessage());
        }
        return null;
    }

    /**
     * map 装换为 java bean 对象
     *
     * @param map  map
     * @param type 指定类型
     * @param <T>  目标对象类型
     * @return 目标对象实例
     */
    public static <T> T mapToBean(Map map, TypeReference<T> type) {
        if (map == null || map.isEmpty()) {
            log.debug("map is empty.");
            return null;
        }
        try {
            return JSON.parseObject(JSON.toJSONString(map), type);
        } catch (Exception e) {
            log.info("beans to maps filter null value failed:{}, error:{}", map, e.getMessage());
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
