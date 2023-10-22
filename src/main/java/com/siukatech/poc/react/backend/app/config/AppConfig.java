package com.siukatech.poc.react.backend.app.config;

import com.siukatech.poc.react.backend.app.data.entity.I18nEntity;
import com.siukatech.poc.react.backend.app.data.entity.ItemEntity;
import com.siukatech.poc.react.backend.app.data.entity.MerchantEntity;
import com.siukatech.poc.react.backend.app.web.model.I18nForm;
import com.siukatech.poc.react.backend.app.web.model.ItemForm;
import com.siukatech.poc.react.backend.app.web.model.MerchantForm;
import com.siukatech.poc.react.backend.parent.EnableReactBackendParent;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Import({
//        GlobalConfigImport.class,
//        WebConfigImport.class
//        , SecurityConfigImport.class
//})
@EnableReactBackendParent
public class AppConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Dont setup the webMvcConfigurer in child microservices
     * @return
     */
//    @Bean
//    public WebMvcConfigurer webMvcConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedMethods(HttpMethod.HEAD.name()
//                                , HttpMethod.GET.name()
//                                , HttpMethod.POST.name()
//                                , HttpMethod.PUT.name()
//                                , HttpMethod.DELETE.name()
//                                , HttpMethod.PATCH.name()
//                                , HttpMethod.OPTIONS.name()
//                        )
//                        .allowedMethods(Arrays.stream(HttpMethod.values()).map(HttpMethod::name).toArray(String[] ::new))
//                        ;
//            }
//
////            @Override
////            public void addInterceptors(InterceptorRegistry registry) {
////                registry.addInterceptor(cryptoPayloadInterceptor);
////            }
//
//        };
//    }


    /**
     * Dont setup the corsFilter in child microservices
     * cors has been setup in parent library WebSecurityConfig
     * OPTIONS
     *
     * @return
     */
//    @Bean
//    public CorsFilter corsFilter() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        final CorsConfiguration config = new CorsConfiguration();
//        //config.setAllowCredentials(true);
////        config.applyPermitDefaultValues();
//        config.setAllowedOrigins(Collections.singletonList("*"));
//        config.setAllowedHeaders(Collections.singletonList("*"));
//        config.setAllowedMethods(Arrays.stream(HttpMethod.values()).map(HttpMethod::name).collect(Collectors.toList()));
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }


//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void configModelMapper() {
//        this.modelMapper.addMappings(new PropertyMap<I18nForm, I18nEntity>() {
//            protected void configure() {
//                skip().setId(null);
//            }
//        });
        this.modelMapper.addMappings(new PropertyMap<I18nForm, I18nEntity>() {
            protected void configure() {
                skip().setId(null);
            }
        });
        try {
            boolean isExisted = this.modelMapper.getTypeMaps().stream()
                    .anyMatch(typeMap -> {
                        logger.debug("configModelMapper - typeMap.getName: [" + typeMap.getName()
                                + "], typeMap.getMappings.size: [" + typeMap.getMappings().size()
                                + "]");
                        typeMap.getMappings().stream().forEach(mapping -> {
                            logger.debug("configModelMapper - mapping.getSourceType.getName: [" + mapping.getSourceType().getName()
                                    + "], mapping.getPath: [" + mapping.getPath()
                                    + "], mapping.toString: [" + mapping.toString()
                                    + "]");
                        });
//                        boolean result = typeMap.equals(this.skipItemFormItemEntityModifiedFieldsMap);
                        boolean result = true;
                        return result;
                    });
            logger.debug("configModelMapper - isExisted: [" + isExisted
                    + "]");
            if (!isExisted) {
                this.modelMapper.addMappings(new PropertyMap<ItemForm, ItemEntity>() {
                    protected void configure() {
                        skip().setId(null);
                    }
                });
            }
        } catch (Exception e) {
            logger.error("configModelMapper - e.getMessage: [" + e.getMessage() + "]", e);
        }
        this.modelMapper.addMappings(new PropertyMap<MerchantForm, MerchantEntity>() {
            protected void configure() {
                skip().setId(null);
            }
        });

    }

}
