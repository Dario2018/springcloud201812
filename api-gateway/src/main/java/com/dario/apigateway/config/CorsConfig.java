package com.dario.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 指定特定接口可跨域
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);//允许Cookies跨域
        config.setAllowedOrigins(Arrays.asList("*"));//http://www.a.com,http://www.b.com等
        config.setAllowedHeaders(Arrays.asList("*"));//所有
        config.setAllowedMethods(Arrays.asList("GET", "POST","DELETE", "OPTIONS", "DELETE"));
        config.setMaxAge(300l);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
