package com.yuexun.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @apiNote 本地缓存Caffeine配置
 * @author zzd
 * @date 2023-08-03 16:59  
 */
@Slf4j
@Configuration
public class CaffeineConfig {

    @Bean
    public Cache<String, Object> cache() {
        final Cache<String, Object> cache = Caffeine.newBuilder()
                // 最后一次写入或最后一次更新后的过期时间
                .expireAfterWrite(30, TimeUnit.SECONDS)
                // 初始的缓存空间大小
                .initialCapacity(10)
                // 缓存的最大条数
                .maximumSize(100)
                //记录下缓存的一些统计数据，例如命中率等
                .recordStats()
                .build();
        log.info("本地缓存Caffeine初始化完成 ...");
        return cache;
    }

}