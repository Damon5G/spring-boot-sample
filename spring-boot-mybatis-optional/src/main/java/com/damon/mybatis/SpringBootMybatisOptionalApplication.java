package com.damon.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.damon.mybatis.mapper")
public class SpringBootMybatisOptionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisOptionalApplication.class, args);
    }

}
