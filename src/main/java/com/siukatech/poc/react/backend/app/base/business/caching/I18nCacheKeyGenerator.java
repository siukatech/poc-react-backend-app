package com.siukatech.poc.react.backend.app.base.business.caching;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

@Slf4j
public class I18nCacheKeyGenerator implements KeyGenerator {

    public static final String CACHE_KEY_findI18nMap = "I18nService.findI18nMap_";

    @Override
    public Object generate(Object target, Method method, Object... params) {
        log.debug("generate - target: [{}], method: [{}], params: [{}]", target, method, params);
        String langTag = params.length > 0 ? (String) params[0] : "";
        String key = CACHE_KEY_findI18nMap + langTag;
        log.debug("generate - target: [{}], langTag: [{}], key: [{}]", target, langTag, key);
        return key;
    }
}
