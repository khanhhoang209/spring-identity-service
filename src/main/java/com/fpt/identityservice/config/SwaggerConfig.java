package com.fpt.identityservice.config;

import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public io.swagger.v3.oas.models.OpenAPI swaggerOpenAPI() {
        return new io.swagger.v3.oas.models.OpenAPI().info( new Info()
                .title("spring-identity-service")
                .version("v1")
        );
    }
}
