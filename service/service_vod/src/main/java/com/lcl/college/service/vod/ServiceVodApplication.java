package com.lcl.college.service.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lcl
 * @date 2020/7/18 14:20
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan({"com.lcl.college"})
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceVodApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class,args);
    }
}
