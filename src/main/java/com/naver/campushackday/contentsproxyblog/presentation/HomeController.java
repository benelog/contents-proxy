package com.naver.campushackday.contentsproxyblog.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naver.campushackday.contentsproxyblog.entity.BlogSetting;
import com.naver.campushackday.contentsproxyblog.service.BlogService;

@Controller
public class HomeController {
	private final BlogService service;
	public HomeController(BlogService service){
		this.service = service;
	}

	@GetMapping("/")
	public ModelAndView home() {
		BlogSetting setting = service.findSetting();
		return new ModelAndView("index")
				.addObject("setting", setting);
	}
}
