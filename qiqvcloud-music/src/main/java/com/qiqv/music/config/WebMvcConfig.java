package com.qiqv.music.config;

import com.qiqv.music.controller.interceptor.MiniInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public MiniInterceptor miniInterceptor(){
        return new MiniInterceptor();
    }

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

    /**
     * 设置拦截的url路径
     *  暂时只针对前端用户评论、收藏、评分功能进行拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List listOfVerify = Arrays.asList("/consumer/**","/rank/rateSongList","/collect/**","/comment/**");
        List listOfExc = Arrays.asList("/consumer/login","/consumer/queryUserById","/consumer/getAllConsumer","/consumer/getVerifyCodePic","/collect/getUserCollect","/comment/query**","/comment/allComment");
        registry.addInterceptor(miniInterceptor()).addPathPatterns(listOfVerify)
                .excludePathPatterns(listOfExc);
        super.addInterceptors(registry);
    }
}
