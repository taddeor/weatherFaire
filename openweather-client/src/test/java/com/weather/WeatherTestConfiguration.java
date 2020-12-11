package com.weather;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
@TestConfiguration
@PropertySource(value = "classpath:test.properties", encoding = "UTF-8")
public class WeatherTestConfiguration {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder().build();
    }
}
