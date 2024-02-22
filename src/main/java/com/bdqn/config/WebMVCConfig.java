package com.bdqn.config;

import com.bdqn.interceptors.LoginProtectedInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

	@Autowired
	private LoginProtectedInterceptor loginProtectedInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginProtectedInterceptor).addPathPatterns("/post/**");

	}


	@Override
	public void addCorsMappings(CorsRegistry registry) {

		//允许跨域访问资源定义
		registry.addMapping("/**")
				//(只允许本地的指定端口访问)允许所有
				.allowedOrigins("http://localhost:9528", "http://localhost:8083")
				// 允许发送凭证: 前端如果配置改属性为true之后，则必须同步配置
				.allowCredentials(true)
				// 允许所有方法
				.allowedMethods("*")
				.allowedHeaders("*");
	}

}
