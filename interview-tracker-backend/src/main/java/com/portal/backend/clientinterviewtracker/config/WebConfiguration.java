package com.portal.backend.clientinterviewtracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "http://172.211.51.130:8001")
                        .allowedMethods("GET", "POST")
                        .allowedHeaders("Content-Type", "Authorization")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}
