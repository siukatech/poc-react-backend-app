package com.siukatech.poc.react.backend.app.item.business.caching;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

@Slf4j
public class ItemCacheKeyGenerator implements KeyGenerator {

    public static final String CACHE_KEY_findItemAll = "ItemService.findItemAll_";

    @Override
    public Object generate(Object target, Method method, Object... params) {
        log.debug("generate - target: [{}], method: [{}], params: [{}]", target, method, params);
        String key = CACHE_KEY_findItemAll;
        log.debug("generate - target: [{}], key: [{}]", target, key);
        return key;
    }
}
