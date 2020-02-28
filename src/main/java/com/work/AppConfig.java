package com.work;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * 使用@Configuration的同时一定要搭配使用@ComponentScan
 */
@Configuration
@ComponentScan
public class AppConfig {


    static ApplicationContext AnnotationConfig() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        return context;
    }
}
