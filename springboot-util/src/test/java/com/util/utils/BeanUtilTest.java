package com.util.utils;


import com.util.entity.Person;
import org.junit.Assert;
import org.junit.Test;

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
    public void testConvertNull() {
        Assert.assertEquals(BeanUtil.convertClass(null, Person.class), null);
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
}