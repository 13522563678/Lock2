package com.example.lock2;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class Lock2Application {

    public static void main(String[] args) {
        System.out.println("999"+1);
        System.out.println("打印"+TT.count);
        TT tt = new TT();
        TT tt2 = new TT();
            System.out.println(tt.hashCode());
            System.out.println(tt2.hashCode());

        SpringApplication.run(Lock2Application.class, args);
    }
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}


class TT{
    public static TT t = new TT();
    public static int count=2;
    public TT() {
        count  ++;
        System.out.println("-----"+count);
    }


}
