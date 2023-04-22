package com.mydesign.employschool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Resource
    HandlerInterceptor handlerInterceptor;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor)
                .addPathPatterns("/campus/**")
                .addPathPatterns("/edu/**")
                .addPathPatterns("/info/**")
                .addPathPatterns("/pos/**")
                .addPathPatterns("/project/**")
                .addPathPatterns("/project/**");
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/campus/admin/**")
                .addPathPatterns("/edu/admin/**")
                .addPathPatterns("/info/admin/**")
                .addPathPatterns("/pos/admin/**")
                .addPathPatterns("/project/admin/**")
                .addPathPatterns("/project/admin/**");
    }
}
