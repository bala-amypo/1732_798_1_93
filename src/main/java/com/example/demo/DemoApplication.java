package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    
    @Bean
    public ServletRegistrationBean<com.example.demo.servlet.SimpleHelloServlet> helloServlet() {
        return new ServletRegistrationBean<>(
            new com.example.demo.servlet.SimpleHelloServlet(), 
            "/hello-servlet"
        );
    }
}