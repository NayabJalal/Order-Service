package com.ecommerce.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableJpaAuditing
public class AppConfig {

    @Bean//register RestTemplate as a bean
    @LoadBalanced//to mark a RestTemplate,RestClient.Builder...Adding loadBalanced on a RestTemplate enables client-side load balancing
    //It intercept the local service name and resolve them into actual instances URLs from eureka
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
