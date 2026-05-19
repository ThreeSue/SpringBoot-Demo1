package com.sue.demo1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 蘇世睿
 * @Class: 移動互聯2412
 * @SeatNo: 21
 * @Description : [统一跨域处理]
 * @Version : [v1.1]
 * @Date: 2026/5/19
 * @Time: 17:01
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**") // 对所有路径应用CORS配置
                .allowedOrigins("*") // 允许的源
                .allowedMethods("*") // 允许的方法
                .allowedHeaders("*") // 允许的头部
                .maxAge(3600); // 预检请求的有效期（秒

    }
}
