package com.siukatech.poc.react.backend.app.config;

import com.siukatech.poc.react.backend.core.EnableReactBackend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Slf4j
@Configuration
//@Import({
//        GlobalConfigImport.class,
//        WebConfigImport.class
//        , SecurityConfigImport.class
//})
@ComponentScan(value = {"com.siukatech.poc.react.backend.core.web.controller"
//        , "com.siukatech.poc.react.backend.core.**.mapper.**"
    }
    , excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX
        , pattern = "com.siukatech.poc.react.backend.core.web.controller.WebController"
    )
)
@EnableReactBackend
public class AppConfig {

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
     * cors has been setup in core library WebSecurityConfig
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


}
