package com.yuexun.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web项目配置类
 * @author :zzd
 * @date : 2023/1/3
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO 本地上传文件的绝对路径
        registry.addResourceHandler("/image/**").addResourceLocations("file:D:\\data\\upload\\");
    }
}
