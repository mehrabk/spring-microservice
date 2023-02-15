package com.mehrab.customer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig {

    @Bean
    @LoadBalanced // if we have 2 instance of FRAUD microservice this usefull route request
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
