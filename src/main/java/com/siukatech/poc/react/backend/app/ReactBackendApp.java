package com.siukatech.poc.react.backend.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

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

    private static final Logger log = LoggerFactory.getLogger(ReactBackendApp.class);

    public static void main(String[] args) {
        List<String> list = List.of(args);
        log.info("main - args: [" + args.length + "]");
        log.info("main - list: [" + list + "]", list);
        SpringApplication.run(ReactBackendApp.class, args);
    }

}
