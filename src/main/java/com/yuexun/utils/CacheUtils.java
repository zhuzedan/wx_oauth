package com.yuexun.utils;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * @apiNote 本地缓存Caffeine工具类
 * @author zzd
 * @date 2023-08-03 16:59
 */
@Component
@RequiredArgsConstructor
public class CacheUtils<K, V> {

    private final Cache<K, V> cache;

    /**
     * @apiNote 依据key获取value, 如果未找到, 返回null
     * @return Object
     */
    public V get(K key) {
        // 就是相当于cache.getIfPresent(key)
        return cache.asMap().get(key);
    }

    /**
     * @apiNote 依据key获取value, 如果未找到, 返回null
     * @return Object
     */
    public V getIfPresent(K key) {
        // 就是相当于get(key)
        return cache.getIfPresent(key);
    }

    /**
     * @apiNote 批量依据key获取value
     * @return Object
     */
    public Map<K, V> getBatch(List<String> key) {
        return cache.getAllPresent(key);
    }

    /**
     * @apiNote 得到缓存Map
     * @return ConcurrentMap<K, V>
     */
    public ConcurrentMap<K, V> get() {
        return cache.asMap();
    }


    /**
     * @apiNote 插入一个缓存
     * @param key   key
     * @param value value
     */
    public void put(K key, V value) {
        cache.put(key, value);
    }

    /**
     * @apiNote 插入缓存,如果不存在，则将value放入缓存
     * @param key   key
     * @param value value
     */
    public V getIfNotExist(K key, V value) {
        return cache.get(key, k -> value);
    }

    /**
     * @apiNote 将一个map插入或修改缓存
     */
    public void putBatch(Map<? extends K, ? extends V> map) {
        cache.asMap().putAll(map);
    }

    /**
     * @apiNote 更新一个指定key的缓存
     * @param key   key
     * @param value value
     */
    public void update(K key, V value) {
        cache.put(key, value);
    }

    /**
     * @apiNote 是否含有指定key的缓存
     * @param key key
     */
    public boolean contains(K key) {
        return cache.asMap().containsKey(key);
    }

    /**
     * @apiNote 删除指定key的缓存
     * @param key key
     */
    public void delete(K key) {
        cache.asMap().remove(key);
    }

    /**
     * @apiNote 批量删除指定key的缓存
     * @param key key
     */
    public void delete(List<String> key) {
        cache.invalidateAll(key);
    }

    /**
     * @apiNote 删除指定key的缓存
     * @param key key
     */
    public void invalidate(K key) {
        cache.invalidate(key);
    }

    /**
     * @apiNote 清除所有缓存
     */
    public void deleteAll() {
        cache.invalidateAll();
    }

}
