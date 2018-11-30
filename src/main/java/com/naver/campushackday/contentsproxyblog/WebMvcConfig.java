package com.naver.campushackday.contentsproxyblog;

import com.naver.campushackday.contentsproxyblog.component.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final LoginInterceptor interceptor;

	public WebMvcConfig(LoginInterceptor interceptor) {
		this.interceptor = interceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor).addPathPatterns("/post/form", "/blog/setting");
	}
}
