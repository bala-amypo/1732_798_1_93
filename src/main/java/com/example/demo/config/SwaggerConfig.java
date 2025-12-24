package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // You need to change the port as per your server
                .servers(List.of(
                        new Server().url("https://9272.pro604cr.amypo.ai//")
                ));
        }
}

curl -v "https://9272.pro604cr.amypo.ai/api/auth/register" -H "*/*" -H "Content-Type: application/json" -d '{
"username":  "abc","email": "abc@gmail.com","password": "abc123" }'