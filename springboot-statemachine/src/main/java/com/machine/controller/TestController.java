package com.machine.controller;

import com.machine.common.cache.CacheRedisService;
import com.machine.entity.Form;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : coofive
 * @version : 1.0.0
 * @date : 10/18/2019 9:46 PM
 */
@RestController
public class TestController {
    @Resource
    private CacheRedisService cacheRedisService;


    @GetMapping("/test")
    public String test(@RequestParam String id){
        return cacheRedisService.getCache(id);
    }

    @GetMapping("/test/2")
    public Form test2(@RequestParam String id){
        return cacheRedisService.getBody(id);
    }
}
