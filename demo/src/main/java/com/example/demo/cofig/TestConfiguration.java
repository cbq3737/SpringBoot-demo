package com.example.demo.cofig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestConfiguration {

    @Bean  // Configuration이니까 Bean을 만들 수 있는 것.
    public String hello()
    {
        return "Hello Test";
    }

}