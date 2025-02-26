package com.example.lock2.controller;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Redisson {

    @Autowired
    private RedissonClient client;
    @RequestMapping("/")
    String home(){
        return "hello";
    }
}
