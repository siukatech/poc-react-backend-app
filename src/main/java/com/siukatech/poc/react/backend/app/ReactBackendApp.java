package com.siukatech.poc.react.backend.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
//        exclude = {SecurityAutoConfiguration.class}
)
//@ComponentScan({
//        "com.siukatech.poc.react.backend.app"
////        , "com.siukatech.poc.react.backend.parent"
//})
//@Import({GlobalExceptionHandler.class, EncryptedRequestBodyAdvice.class})
//@Import({
//        GlobalConfigImport.class,
//        WebConfigImport.class
//        , SecurityConfigImport.class
//})
// No need to enable @EnableConfigurationProperties
//@EnableConfigurationProperties
public class ReactBackendApp {

    public static void main(String[] args) {
        SpringApplication.run(ReactBackendApp.class, args);
    }

}
