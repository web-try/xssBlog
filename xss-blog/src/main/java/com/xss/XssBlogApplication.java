package com.xss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan("com.xss.mapper")
public class XssBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(XssBlogApplication.class, args);
    }
}
