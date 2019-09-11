package com.wechat.bot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@MapperScan("com.wechat.bot.mapper")
@SpringBootApplication
public class BotApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BotApplication.class);
    }

}
