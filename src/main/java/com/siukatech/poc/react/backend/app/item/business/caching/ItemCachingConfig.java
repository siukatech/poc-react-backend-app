package com.siukatech.poc.react.backend.app.item.business.caching;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemCachingConfig {

    public static final String CACHE_NAME_ITEM = "item";

    @Bean
    public ItemCacheKeyGenerator itemCacheKeyGenerator() {
        return new ItemCacheKeyGenerator();
    }
}
