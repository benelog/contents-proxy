package com.naver.campushackday.contentsproxyblog.presentation;

import com.naver.campushackday.contentsproxyblog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/blog/setting")
public class BlogSettingController {

	private final BlogService blogService;

	public BlogSettingController(BlogService blogService) {
		this.blogService = blogService;
	}

	@GetMapping
	public ModelAndView getGithubBlogTitle() {
		return new ModelAndView("blogSetting")
				.addObject("title", blogService.findGithubBlogTitle());
	}

	@PutMapping
	public ModelAndView modifyGithubBlogTitle(String newTitle) {
		blogService.updateBlogName(newTitle);

		return new ModelAndView("blogSetting")
				.addObject("title", blogService.findGithubBlogTitle());
	}
}

