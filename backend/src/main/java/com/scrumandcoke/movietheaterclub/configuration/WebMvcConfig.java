package com.scrumandcoke.movietheaterclub.configuration;

import com.scrumandcoke.movietheaterclub.interceptor.LoginRequiredInterceptor;
import com.scrumandcoke.movietheaterclub.service.IAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private IAMService iamService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginRequiredInterceptor(iamService));
    }
}

