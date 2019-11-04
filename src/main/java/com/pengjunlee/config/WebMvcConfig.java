package com.pengjunlee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author pengjunlee
 * @create 2019-09-11 17:29
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 允许任何域名请求
                .allowedOrigins("*")
                // 允许的请求类型
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                // 响应的最大缓存时间
                .maxAge(3600)
                // 允许携带鉴权信息
                .allowCredentials(true)
                // 允许返回的响应头列表
                .exposedHeaders(
                        "access-control-allow-headers",
                        "access-control-allow-origin",
                        "access-control-allow-methods",
                        "access-control-max-age",
                        "x-authorization-with");
    }
}