package com.qiqv.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 将 /img/singerPic/** 的请求定位到本地项目的 /img/singerPic/ 目录下
 */
@Configuration
public class FilePicConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        // 歌手头像
        registry.addResourceHandler("/img/singerPic/**")
                .addResourceLocations("file:"+System.getProperty("user.dir") + System.getProperty("file.separator")
                + "img" + System.getProperty("file.separator") + "singerPic" + System.getProperty("file.separator"));
        // 歌曲图片
        registry.addResourceHandler("/img/songPic/**")
                .addResourceLocations("file:"+System.getProperty("user.dir") + System.getProperty("file.separator")
                + "img" + System.getProperty("file.separator") +"songPic" + System.getProperty("file.separator"));
        // 歌曲
        registry.addResourceHandler("/song/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + System.getProperty("file.separator")
                + "song" + System.getProperty("file.separator"));
        // 歌单图片
        registry.addResourceHandler("/img/songListPic/**")
                .addResourceLocations("file:"+System.getProperty("user.dir") + System.getProperty("file.separator")
                + "img" + System.getProperty("file.separator") + "songListPic" + System.getProperty("file.separator"));
        // 前端用户头像
        registry.addResourceHandler("/img/avatorImages/**")
                .addResourceLocations("file:"+System.getProperty("user.dir") + System.getProperty("file.separator")
                        + "img" + System.getProperty("file.separator") + "avatorImages" + System.getProperty("file.separator"));
    }

}
