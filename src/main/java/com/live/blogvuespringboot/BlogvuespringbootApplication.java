package com.live.blogvuespringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.live.blogvuespringboot.mapper")
public class BlogvuespringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogvuespringbootApplication.class, args);
    }

}
