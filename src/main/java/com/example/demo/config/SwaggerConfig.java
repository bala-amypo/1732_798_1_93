package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        // Define the security scheme for JWT
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("Authorization")
                .description("JWT Authorization header using the Bearer scheme. Enter your token in the format: Bearer {token}")
                .in(SecurityScheme.In.HEADER);
        
        // Add security requirement
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Bearer Authentication");
        
        return new OpenAPI()
                .servers(List.of(
                        new Server()
                                .url("https://9272.pro604cr.amypo.ai")  // Fixed: removed trailing slash
                                .description("Production Server"),
                        new Server()
                                .url("http://localhost:9001")
                                .description("Local Development Server")
                ))
                .info(new Info()
                        .title("Drug Interaction Checker API")
                        .version("1.0")
                        .description("API for checking medication interactions")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", securityScheme))
                .addSecurityItem(securityRequirement);
    }
}