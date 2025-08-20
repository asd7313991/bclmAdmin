//package org.example.properties.tenant.core;
//
//import lombok.extern.slf4j.Slf4j;
//import org.example.properties.tenant.TenantContextHolder;
//import org.springframework.cache.Cache;
//
//@Slf4j
//public class TenantRedisCacheManager extends RedisCacheManager {
//
//    public TenantRedisCacheManager(RedisCacheWriter cacheWriter,
//                                   RedisCacheConfiguration defaultCacheConfiguration) {
//        super(cacheWriter, defaultCacheConfiguration);
//    }
//
//    @Override
//    public Cache getCache(String name) {
//        // 如果开启多租户，则 name 拼接租户后缀
//        if (!TenantContextHolder.isIgnore()
//                && TenantContextHolder.getTenantId() != null) {
//            name = name + ":" + TenantContextHolder.getTenantId();
//        }
//
//        // 继续基于父方法
//        return super.getCache(name);
//    }
//
//}
