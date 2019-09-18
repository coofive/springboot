package com.util.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author wenwu.liu.o
 */
public class JacksonUtilTest {

    @Test
    public void objectToJsonIfObjIsNull() {
        Assert.assertEquals(JacksonUtil.objectToJson(null), null);
    }

    @Test
    public void objectToJsonIfObjIsString() {
        Assert.assertEquals(JacksonUtil.objectToJson("str"), "str");
    }

    @Test
    public void objectToJsonIfObjIsObj() {
        Map<String, String> map = new HashMap<>();
        map.put("test", "test");
        Assert.assertEquals(JacksonUtil.objectToJson(map), "{\"test\":\"test\"}");
    }

    @Test
    public void jsonToObjectIfJsonIsEmpty() {
        Assert.assertEquals(JacksonUtil.jsonToObject(null, Map.class), null);
        Assert.assertEquals(JacksonUtil.jsonToObject("", Map.class), null);
        Assert.assertEquals(JacksonUtil.jsonToObject(null, new TypeReference<Map>() {
        }), null);
        Assert.assertEquals(JacksonUtil.jsonToObject("", new TypeReference<Map>() {
        }), null);
    }

    @Test
    public void jsonToObjectIfJsonIsString() {
        Assert.assertEquals(JacksonUtil.jsonToObject("str", String.class), "str");
        Assert.assertEquals(JacksonUtil.jsonToObject("str", new TypeReference<String>() {
        }), "str");
    }

    @Test
    public void jsonToObjectIfJsonIsObj() {
        Map<String, String> map = new HashMap<>();
        map.put("test", "test");
        Assert.assertEquals(JacksonUtil.jsonToObject("{\"test\":\"test\"}", Map.class), map);
        Assert.assertEquals(JacksonUtil.jsonToObject("{\"test\":\"test\"}", new TypeReference<Map>() {
        }), map);
    }
}