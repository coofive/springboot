package com.util.utils;


import com.util.entity.Dept;
import com.util.entity.Person;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author : coofive
 * @version : 1.0.0
 * @date : 6/2/2019 12:28 PM
 */
public class FastJsonUtilTest {

    private Person obj;
    private String jsonObj;

    @Before
    public void setUp() {
        obj = new Person();
        obj.setId(1);
        obj.setName("TEST");
        obj.setGroupNames(Lists.newArrayList("Lucy","Bob"));
        obj.setDept(new Dept().setId(2).setDeptName("TEST DEPT 1"));
        obj.setDeptList(Lists.newArrayList(new Dept().setId(3).setDeptName("TEST DEPT 2")));

        jsonObj = "{\"dept\":{\"deptName\":\"TEST DEPT 1\",\"id\":2},\"deptList\":[{\"deptName\":\"TEST DEPT 2\",\"id\":3}],\"groupNames\":[\"Lucy\",\"Bob\"],\"id\":1,\"name\":\"TEST\"}";
    }

    @Test
    public void testObjectToJson() {
        String jsonString = FastJsonUtil.objectToJson(obj);
        Assert.assertEquals(jsonObj,jsonString);
    }
}