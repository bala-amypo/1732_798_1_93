package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server()
                                .url("https://9134.408procr.amypo.ai")
                                .description("Production Server")
                ))
                .info(new Info()
                        .title("Drug Interaction Checker API")
                        .version("1.0")
                        .description("API for checking medication interactions"));
        // No security scheme configuration
    }
}