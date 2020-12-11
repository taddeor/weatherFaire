package com.weather.configuration;

import com.weather.api.invoker.ApiInvoker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class WeatherClintConfiguration {
    @Bean
    public ApiInvoker apiInvoker(@Value("${base.path}") String basePath,
                                 RestTemplate restTemplate){
        log.info("openweather Server base path {}",basePath);
        return ApiInvoker.from(basePath,restTemplate);
    }


}
