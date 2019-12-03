package com.machine.common.cache;

import com.machine.entity.Form;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author : coofive
 * @version : 1.0.0
 * @date : 10/18/2019 8:42 PM
 */
@Slf4j
@Service
public class CacheRedisService {


    @Cacheable(value = "UserInfoList", cacheManager = CacheConfig.CacheManagerName.CUSTOM_CACHE_MANAGER, key = "'FORM:'+#id")
    public String getCache(String id) {
        System.out.println("id = " + id);
        return "testCache" + ":" + id;
    }

    @Cacheable(cacheManager = CacheConfig.CacheManagerName.CUSTOM_CACHE_MANAGER, key = "'FORM:'+#id")
    public Form getBody(String id) {
        System.out.println("id = " + id);
        CompletableFuture<Form> future = getAsyncBody(id);
        Form form = future.exceptionally(e -> {
            log.error(e.getMessage(), e);
            return null;
        }).join();

        return new Form().setFormId(id);
    }

    @Async
    public CompletableFuture<Form> getAsyncBody(String id) {
        System.out.println("id = " + id);
        return CompletableFuture.completedFuture(new Form().setFormId(id));
    }
}
