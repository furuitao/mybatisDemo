package com.finup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by furuitao on 2017/11/6.
 */
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
@ComponentScan("com.finup")
//@EnableScheduling
@EnableCaching
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }


}
