package com.naver.campushackday.contentsproxyblog;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EncryptablePropertySource(name = "EncryptedProperties", value = "classpath:application.properties")
public class ContentsProxyBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentsProxyBlogApplication.class, args);
	}
}
