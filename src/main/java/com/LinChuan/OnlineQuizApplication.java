package com.LinChuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.LinChuan.mapper")
public class OnlineQuizApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineQuizApplication.class, args);
    }
}
