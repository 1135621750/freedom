package com.freedom.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public MyYml myYml(){
        return new MyYml();
    }
}
