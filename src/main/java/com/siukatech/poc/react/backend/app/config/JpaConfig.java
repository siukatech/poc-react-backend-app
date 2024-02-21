package com.siukatech.poc.react.backend.app.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EntityScan(basePackages = {"com.siukatech.poc.react.backend.app.data.entity"})
@EnableJpaRepositories("com.siukatech.poc.react.backend.app.data.repository")
public class JpaConfig {

//    @Bean
//    public AuditorAware<String> auditorAware() {
//        return new AuditorAware<String>() {
//            @Override
//            public Optional<String> getCurrentAuditor() {
//                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                String name = authentication.getName();
//                return Optional.of(name);
//            }
//        };
//    }

}
