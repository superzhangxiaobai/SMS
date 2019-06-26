package com.xiaobai.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class MvcConfig extends WebMvcConfigurerAdapter  {

	//直接页面跳转，不经过Controller，这样在没有任何处理业务的时候,快捷的页面转向定义会节省好多代码
	@Override
	 public void addViewControllers(ViewControllerRegistry registry)
	 {
	 	registry.addViewController("/home").setViewName("sys/home");
		registry.addViewController("/").setViewName("sys/login");
	 }

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//允许各种请求,请求头, 请求源
		registry.addMapping("/**").allowedHeaders("*").allowedOrigins("*");
	}
}
