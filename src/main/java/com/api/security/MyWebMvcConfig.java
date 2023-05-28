// package com.api.security;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class MyWebMvcConfig implements WebMvcConfigurer {

// @Override
// public void addCorsMappings(CorsRegistry registry) {
// registry
// .addMapping("/*")
// .allowedOrigins("http://localhost:5173/")
// .allowedMethods("GET", "POST", "OPTIONS", "PUT")
// .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin",
// "Access-Control-Request-Method",
// "Access-Control-Request-Headers")
// .exposedHeaders("Access-Control-Allow-Origin",
// "Access-Control-Allow-Credentials")
// .allowCredentials(true).maxAge(3600);
// ;

// WebMvcConfigurer.super.addCorsMappings(registry);
// }

// }
