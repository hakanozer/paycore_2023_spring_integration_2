package com.works.configs;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppBeans {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();
        caches.add( new ConcurrentMapCache("note"));
        caches.add( new ConcurrentMapCache("product"));
        manager.setCaches(caches);
        return manager;
    }

}
