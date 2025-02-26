package com.example.lock2.controller;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Proxy;

@RequestMapping
@RestController
public class SentinelController {

    @RequestMapping("limit")
    public String limit() {

        return "success";
    }

    @RequestMapping("/fallback/slow")
    public String slow() {
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }


    @RequestMapping("/fallback/exception")
    public String exception(@RequestParam Integer param) {
        if (param == 1) {
            throw new RuntimeException();
        }
        return "success";
    }


    @RequestMapping("hotParams")
    public String hotParams(@RequestParam String param) {

        return "success" + param;
    }

    @RequestMapping("resource")
    public String resource() {
        // your business logic
        return SpringUtil.getBean(SentinelController.class).doSomething();
    }

    @RequestMapping("resourceChain")
    public String resourceChain() {
        // your business logic
        return SpringUtil.getBean(SentinelController.class).doSomething();
    }

    @SentinelResource(value = "doSomething", blockHandler = "handleBlock")
    public String doSomething() {
        // your business logic
        return "success";
    }

    public String handleBlock(BlockException ex) {
        // handle exception here
        return "Blocked";
    }


}
