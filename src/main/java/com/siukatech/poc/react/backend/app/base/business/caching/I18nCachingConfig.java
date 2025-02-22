package com.siukatech.poc.react.backend.app.base.business.caching;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class I18nCachingConfig {

    public static final String CACHE_NAME_I18N = "i18n";

    @Bean
    public I18nCacheKeyGenerator i18nCacheKeyGenerator() {
        return new I18nCacheKeyGenerator();
    }
}
