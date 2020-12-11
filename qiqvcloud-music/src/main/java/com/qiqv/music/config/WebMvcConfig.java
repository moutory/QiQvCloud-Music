package com.qiqv.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 解决跨域问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 可以被跨域的路径
                .allowedOrigins("*") // 运行跨域的请求域名
                .allowedMethods("*") // 允许跨域访问服务器资源的请求方式
                .allowCredentials(true); //允许跨域
    }
}
