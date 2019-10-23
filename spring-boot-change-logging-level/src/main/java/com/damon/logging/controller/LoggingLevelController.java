package com.damon.logging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingLevelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingLevelController.class);

    @GetMapping("/test")
    public String simple() {
        LOGGER.debug("这是一个debug日志...");
        return "test";
    }
}
