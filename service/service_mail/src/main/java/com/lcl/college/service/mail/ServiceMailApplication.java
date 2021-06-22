package com.lcl.college.service.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lcl
 * @date 2020/7/29 18:37
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.lcl.college"})
@EnableDiscoveryClient
public class ServiceMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMailApplication.class, args);
    }

}