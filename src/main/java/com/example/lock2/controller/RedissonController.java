package com.example.lock2.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.redisson.api.RedissonClient;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RedissonController {

    @Autowired
    private RedissonClient redissonClient;
    private static final Log log2 = LogFactory.getLog(RedissonController.class);


    @GetMapping("/lock")
    public String acquireLock(@RequestParam("id") Long id) {
        log2.info("id=" + id + " 获取锁，线程id=" + Thread.currentThread().threadId());
        RLock lock = redissonClient.getLock("myLock" + id);

        // 尝试获取锁
        boolean b = lock.tryLock();

        if (!b) {
            return "获取锁失败";
        }
        log2.info("id=" + id + " 成功获取锁，线程id=" + Thread.currentThread().threadId());

        try {
            // 执行需要锁保护的业务逻辑
            return "Lock acquired!";
        } finally {
            // 释放锁
            log2.info("id=" + id + " 释放锁，线程id=" + Thread.currentThread().threadId());

            lock.unlock();
            log2.info("id=" + id + " 释放锁成功，线程id=" + Thread.currentThread().threadId());

        }
    }

    @GetMapping("/lock2")
    public String acquireLock2() {
        UUID uuid = UUID.randomUUID();

        RLock lock = redissonClient.getLock("myLock" + uuid);

        // 尝试获取锁
        boolean b = lock.tryLock();
        lock.tryLock();lock.tryLock();lock.tryLock();lock.tryLock();lock.tryLock();

        if (!b) {
            log2.info("锁获取失败");
        }
        log2.info("id=" + uuid + " 成功获取锁，线程id=" + Thread.currentThread().threadId());

        try {
            // 执行需要锁保护的业务逻辑
            return "Lock acquired!";
        } finally {
            // 释放锁
            lock.unlock();
            log2.info("id=" + uuid + " 释放锁成功，线程id=" + Thread.currentThread().threadId());

        }

    }

    @GetMapping("/lock3")
    public String acquireLock3() {
        UUID uuid = UUID.randomUUID();


        // 尝试获取锁

        log2.info("id=" + uuid + " 成功获取锁，线程id=" + Thread.currentThread().threadId());

        try {
            // 执行需要锁保护的业务逻辑
            return "Lock acquired!";
        } finally {
            // 释放锁
            log2.info("id=" + uuid + " 释放锁成功，线程id=" + Thread.currentThread().threadId());

        }

    }

    @GetMapping("/unlock")
    public String unlock(@RequestParam("id") Long id) {
        log2.info("id=" + id + " 获取锁，线程id=" + Thread.currentThread().threadId());
        RLock lock = redissonClient.getLock("myLock" + id);

        // 尝试获取锁
        try {
            lock.unlock();
            return "解锁成功";
        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }
}
