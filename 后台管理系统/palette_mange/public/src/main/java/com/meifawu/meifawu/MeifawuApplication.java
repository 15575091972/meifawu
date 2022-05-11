package com.meifawu.meifawu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.meifawu.meifawu.dao")
public class MeifawuApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeifawuApplication.class, args);
    }

}
