package com.mtmn.javamicro.stockservice.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        Logger logger = LoggerFactory.getLogger(Config.class);
        logger.info("Creating a REST TEMPLATE");
        return new RestTemplate();
    }

}
