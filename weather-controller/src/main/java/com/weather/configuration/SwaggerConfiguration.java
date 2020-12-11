package com.weather.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
@RequiredArgsConstructor
@Configuration
public class SwaggerConfiguration {

    protected final Environment environment;

    /**
     * Swagger configuration open API v3
     * @return
     */
    @Bean
    public OpenAPI api() {

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER).name("Authorization");

        final OpenAPI bearerAuth = new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .info(new Info().title(String.format("%s mvc doc", environment.getProperty("spring.application.name")))
                        .version("1").
                                description(environment.getProperty("application.swagger.description")));


        return bearerAuth;
    }
}
