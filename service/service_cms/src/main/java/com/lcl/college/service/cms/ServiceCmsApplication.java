package com.lcl.college.service.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lcl
 * @date 2020/7/28 17:16
 **/
@SpringBootApplication
@ComponentScan({"com.lcl.college"})
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmsApplication.class, args);
    }
}
