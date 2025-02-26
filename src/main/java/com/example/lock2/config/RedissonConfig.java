package com.example.lock2.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        // 创建 Redisson 配置
        Config config = new Config();
        config.setLockWatchdogTimeout(300*1000);
        // 配置单节点 Redis 连接
        SingleServerConfig serverConfig = config.useSingleServer();
        serverConfig.setAddress("redis://127.0.0.1:6379"); // Redis 服务器地址，使用 "redis://"
        serverConfig.setPassword(null);  // 如果需要密码，可以设置密码
        serverConfig.setConnectionPoolSize(64);  // 设置连接池大小
        serverConfig.setConnectionMinimumIdleSize(24);  // 设置最小空闲连接数
        // 创建 Redisson 实例
        return Redisson.create(config);
    }
}
