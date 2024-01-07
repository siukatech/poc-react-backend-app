package com.siukatech.poc.react.backend.app.config;

import com.siukatech.poc.react.backend.parent.EnableReactBackendParent;
import com.siukatech.poc.react.backend.parent.business.form.AbstractForm;
import com.siukatech.poc.react.backend.parent.data.entity.AbstractEntity;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import org.apache.commons.lang3.ClassUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
////        this.modelMapper.addMappings(new PropertyMap<I18nForm, I18nEntity>() {
////            protected void configure() {
////                skip().setId(null);
////            }
////        });
//        this.modelMapper.addMappings(new PropertyMap<I18nForm, I18nEntity>() {
//            protected void configure() {
//                skip().setId(null);
//            }
//        });
//        try {
//            boolean isExisted = this.modelMapper.getTypeMaps().stream()
//                    .anyMatch(typeMap -> {
//                        logger.debug("configModelMapper - typeMap.getName: [" + typeMap.getName()
//                                + "], typeMap.getMappings.size: [" + typeMap.getMappings().size()
//                                + "]");
//                        typeMap.getMappings().stream().forEach(mapping -> {
//                            logger.debug("configModelMapper - mapping.getSourceType.getName: [" + mapping.getSourceType().getName()
//                                    + "], mapping.getPath: [" + mapping.getPath()
//                                    + "], mapping.toString: [" + mapping.toString()
//                                    + "]");
//                        });
////                        boolean result = typeMap.equals(this.skipItemFormItemEntityModifiedFieldsMap);
//                        boolean result = true;
//                        return result;
//                    });
//            logger.debug("configModelMapper - isExisted: [" + isExisted
//                    + "]");
//            if (!isExisted) {
//                this.modelMapper.addMappings(new PropertyMap<ItemForm, ItemEntity>() {
//                    protected void configure() {
//                        skip().setId(null);
//                    }
//                });
//            }
//        } catch (Exception e) {
//            logger.error("configModelMapper - e.getMessage: [" + e.getMessage() + "]", e);
//        }
//        this.modelMapper.addMappings(new PropertyMap<MerchantForm, MerchantEntity>() {
//            protected void configure() {
//                skip().setId(null);
//            }
//        });

        // https://www.baeldung.com/java-scan-annotations-runtime
        Reflections reflections = new Reflections(this.getClass().getPackageName());
        Set<Class<?>> entityTypes = reflections.getTypesAnnotatedWith(Entity.class);
        entityTypes.forEach(entityType -> {
            String formTypeName = entityType.getName().replaceFirst("Entity", "Form");
            try {
                Class formType = ClassUtils.getClass(formTypeName);
                AbstractEntity entityObj = (AbstractEntity) entityType.getDeclaredConstructor().newInstance();
                AbstractForm formObj = (AbstractForm) formType.getDeclaredConstructor().newInstance();
                registerPropertyMap(formObj, entityObj);
            } catch (ClassNotFoundException
                     | InvocationTargetException
                     | InstantiationException
                     | IllegalAccessException
                     | NoSuchMethodException
                    e) {
                //throw new RuntimeException(e);
                // do nothing
                logger.error("configModelMapper - e.getMessage: " + e.getMessage(), e);
            }
        });

    }

    private <F extends AbstractForm, E extends AbstractEntity> void registerPropertyMap(
            F abstractForm, E abstractEntity) {
        this.modelMapper.addMappings(new PropertyMap<F, E>() {
            protected void configure() {
                skip().setId(null);
            }
        });
    }

}
