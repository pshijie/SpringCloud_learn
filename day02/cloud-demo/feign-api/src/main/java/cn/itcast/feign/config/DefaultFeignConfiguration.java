package cn.itcast.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;


/**
 * @author psj
 * @date 2023/3/25 21:06
 * @File: DefaultFeignConfiguration.java
 * @Software: IntelliJ IDEA
 */

public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.BASIC;
    }
}
