package com.lixianda;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lixianda.mapper")
public class OnlineQuizApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineQuizApplication.class, args);
    }
}
