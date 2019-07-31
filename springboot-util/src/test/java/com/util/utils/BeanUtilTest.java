package com.util.utils;


import com.alibaba.fastjson.TypeReference;
import com.util.entity.Person;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author : coofive
 * @version : 1.0.0
 * @date : 6/2/2019 1:56 PM
 */
public class BeanUtilTest {

    @Test
    public void testEqualsNullAndNull() {
        Assert.assertEquals(BeanUtil.equal(null, null), true);
    }

    @Test
    public void testEqualsNullAndObj() {
        Assert.assertEquals(BeanUtil.equal(null, new Person()), false);
    }

    @Test
    public void testEqualsObjAndNull() {
        Assert.assertEquals(BeanUtil.equal(new Person(), null), false);
    }

    @Test
    public void testEqualsObjAndObj() {
        Person p1 = new Person().setName("TEST");
        Person p2 = new Person().setName("TEST");
        Assert.assertEquals(BeanUtil.equal(p1, p2), true);
    }

    @Test
    public void testNotEqualsNullAndNull() {
        Assert.assertEquals(BeanUtil.notEqual(null, null), false);
    }

    @Test
    public void testNotEqualsNullAndObj() {
        Assert.assertEquals(BeanUtil.notEqual(null, new Person()), true);
    }

    @Test
    public void testNotEqualsObjAndNull() {
        Assert.assertEquals(BeanUtil.notEqual(new Person(), null), true);
    }

    @Test
    public void testNotEqualsObjAndObj() {
        Person p1 = new Person().setName("TEST");
        Person p2 = new Person().setName("TEST");
        Assert.assertEquals(BeanUtil.notEqual(p1, p2), false);
    }

    @Test
    public void testConvertClass() {
        Assert.assertEquals(BeanUtil.convertClass(null, new TypeReference<Person>() {
        }), null);
        Assert.assertEquals(BeanUtil.convertClass(new Person().setId(1), new TypeReference<Person>() {
        }).getId(), new Integer(1));
    }

    @Test
    public void testConvertNull() {
        Assert.assertEquals(BeanUtil.convertClass((Person) null, Person.class), null);
    }

    /**
     * 转化过的对象，属性值相等，真实hashCode不相等
     */
    @Test
    public void testConvertObjectClass() {
        Person p1 = new Person().setName("TEST");
        Person p2 = BeanUtil.convertClass(p1, Person.class);
        // 属性值相等
        Assert.assertEquals(p1.equals(p2), true);

        // 真实hashCode不一样
        System.out.println("System.identityHashCode(p1) = " + System.identityHashCode(p1));
        System.out.println("System.identityHashCode(p2) = " + System.identityHashCode(p2));
        boolean result = System.identityHashCode(p1) == System.identityHashCode(p2);
        Assert.assertEquals(result, false);
    }

    @Test
    public void testConvertClassFilterNameIsNull() {
        Person p1 = new Person().setName("TEST");
        String s = null;
        Person p2 = BeanUtil.convertClass(p1, Person.class, s);

        // 属性值相等
        Assert.assertEquals(p1.equals(p2), true);
    }

    @Test
    public void testConvertClassFilterNameIsEmpty() {
        Person p1 = new Person().setName("TEST");
        Person p2 = BeanUtil.convertClass(p1, Person.class, "");

        // 属性值相等
        Assert.assertEquals(p1.equals(p2), true);
    }

    @Test
    public void testConvertClassFilterNameIsName() {
        Person p1 = new Person().setName("TEST");
        Person p2 = BeanUtil.convertClass(p1, Person.class, "name");

        // 属性值相等
        Assert.assertEquals(p1.equals(p2), false);
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
    }

    @Test
    public void testNullObjectIsEmpty() {
        Object obj = null;
        Assert.assertEquals(BeanUtil.isEmpty(obj), true);
    }

    @Test
    public void testNewObjectIsNotEmpty() {
        Object obj = new Object();
        Assert.assertEquals(BeanUtil.isNotEmpty(obj), true);
    }

    @Test
    public void testStringIsEmpty() {
        Assert.assertEquals(BeanUtil.isEmpty(""), true);
        Assert.assertEquals(BeanUtil.isEmpty((String) null), true);
    }

    @Test
    public void testStringIsNotEmpty() {
        Assert.assertEquals(BeanUtil.isNotEmpty("TEST"), true);
        Assert.assertEquals(BeanUtil.isNotEmpty(" "), true);
    }

    @Test
    public void testMapIsEmpty() {
        Assert.assertEquals(BeanUtil.isEmpty(new HashMap<>()), true);
        Assert.assertEquals(BeanUtil.isEmpty(new HashMap<>(1)), true);
    }

    @Test
    public void testMapIsNotEmpty() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "TEST");
        Assert.assertEquals(BeanUtil.isNotEmpty(map), true);
        Map<String, String> map2 = new HashMap<>();
        map2.put(null, null);
        Assert.assertEquals(BeanUtil.isNotEmpty(map2), true);
    }

    @Test
    public void testListIsEmpty() {
        Assert.assertEquals(BeanUtil.isEmpty(Lists.newArrayList()), true);
        Assert.assertEquals(BeanUtil.isEmpty(new ArrayList<>()), true);
    }

    @Test
    public void testListIsNotEmpty() {
        Assert.assertEquals(BeanUtil.isNotEmpty(Lists.newArrayList("TEST")), true);
        Assert.assertEquals(BeanUtil.isNotEmpty(Lists.newArrayList((Object) null)), true);
    }

    @Test
    public void testArrayIsEmpty() {
        Assert.assertEquals(BeanUtil.isEmpty(new Person[]{}), true);
        Assert.assertEquals(BeanUtil.isEmpty(new Object[]{}), true);
        Assert.assertEquals(BeanUtil.isEmpty(new short[]{}), true);
        Assert.assertEquals(BeanUtil.isEmpty(new int[]{}), true);
        Assert.assertEquals(BeanUtil.isEmpty(new long[]{}), true);
        Assert.assertEquals(BeanUtil.isEmpty(new float[]{}), true);
        Assert.assertEquals(BeanUtil.isEmpty(new double[]{}), true);
        Assert.assertEquals(BeanUtil.isEmpty(new char[]{}), true);
        Assert.assertEquals(BeanUtil.isEmpty(new boolean[]{}), true);
        Assert.assertEquals(BeanUtil.isEmpty(new byte[]{}), true);
        Assert.assertEquals(BeanUtil.isEmpty(new byte[]{}), true);
    }

    @Test
    public void testArrayIsNotEmpty() {
        Assert.assertEquals(BeanUtil.isNotEmpty(new Person[]{null}), true);
        Assert.assertEquals(BeanUtil.isNotEmpty(new Object[]{null}), true);
        Assert.assertEquals(BeanUtil.isNotEmpty(new short[]{1}), true);
        Assert.assertEquals(BeanUtil.isNotEmpty(new int[]{1}), true);
        Assert.assertEquals(BeanUtil.isNotEmpty(new long[]{1}), true);
        Assert.assertEquals(BeanUtil.isNotEmpty(new float[]{1}), true);
        Assert.assertEquals(BeanUtil.isNotEmpty(new double[]{1}), true);
        Assert.assertEquals(BeanUtil.isNotEmpty(new char[]{1}), true);
        Assert.assertEquals(BeanUtil.isNotEmpty(new boolean[]{true}), true);
        Assert.assertEquals(BeanUtil.isNotEmpty(new byte[]{1}), true);
    }

    @Test
    public void beanToMap() {
        Assert.assertEquals(BeanUtil.beanToMap(null), new HashMap<>());

        Person p1 = new Person().setId(1).setName("test");
        Map<String, Object> map = BeanUtil.beanToMap(p1);
        Assert.assertEquals(map.size() > 2, true);
    }

    @Test
    public void beanToMapNotNull() {
        Assert.assertEquals(BeanUtil.beanToMapNotNull(null), new HashMap<>());

        Person p1 = new Person().setId(1).setName("test");
        Map<String, Object> map = BeanUtil.beanToMapNotNull(p1);
        Assert.assertEquals(map.size(), 2);
    }

    @Test
    public void beansToMaps() {
        Assert.assertEquals(BeanUtil.beansToMaps(Lists.newArrayList()), Collections.emptyList());
        Assert.assertEquals(BeanUtil.beansToMaps(null), Collections.emptyList());
        Person p1 = new Person().setId(1).setName("test");
        Assert.assertEquals(BeanUtil.beansToMaps(Lists.newArrayList(p1)).size(), 1);
    }

    @Test
    public void beansToMapsNotNull() {
        Assert.assertEquals(BeanUtil.beansToMaps(Lists.newArrayList()), Collections.emptyList());
        Assert.assertEquals(BeanUtil.beansToMaps(null), Collections.emptyList());
        Person p1 = new Person().setId(1).setName("test");
        Assert.assertEquals(BeanUtil.beansToMapsNotNull(Lists.newArrayList(p1)).size(), 1);
        Person p2 = new Person();
        List<Map<String, Object>> maps = BeanUtil.beansToMapsNotNull(Lists.newArrayList(p2));
        Assert.assertEquals(maps.size(), 0);
    }

    @Test
    public void mapToBean() {
        Assert.assertEquals(BeanUtil.mapToBean(null, Person.class), null);
        Assert.assertEquals(BeanUtil.mapToBean(new HashMap(), Person.class), null);

        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        Person person = BeanUtil.mapToBean(map, Person.class);
        Assert.assertEquals(person.getId(), new Integer(1));
    }


    @Test
    public void mapToBeanByType() {
        Assert.assertEquals(BeanUtil.mapToBean(null, Person.class), null);
        Assert.assertEquals(BeanUtil.mapToBean(new HashMap(), Person.class), null);

        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        Person person = BeanUtil.mapToBean(map, new TypeReference<Person>() {
        });
        Assert.assertEquals(person.getId(), new Integer(1));
    }
}