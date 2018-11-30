package com.naver.campushackday.contentsproxyblog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContentsProxyBlogApplication {

	private static final Logger log = LoggerFactory.getLogger(ContentsProxyBlogApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ContentsProxyBlogApplication.class, args);
	}
}
