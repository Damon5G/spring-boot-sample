package com.damon.mybatis.controller;

import com.damon.mybatis.entity.User;
import com.damon.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userMapper.selectById(id).
                orElseThrow(() -> new IllegalArgumentException("this user does not exist!"));
    }
}
