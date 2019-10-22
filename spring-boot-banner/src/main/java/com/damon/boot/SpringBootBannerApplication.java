package com.damon.boot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBannerApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringBootBannerApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
        //SpringApplication.run(SpringBootBannerApplication.class, args);
    }

}
