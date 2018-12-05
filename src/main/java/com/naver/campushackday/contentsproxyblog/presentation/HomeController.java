package com.naver.campushackday.contentsproxyblog.presentation;

import com.naver.campushackday.contentsproxyblog.dto.PostDto;
import com.naver.campushackday.contentsproxyblog.entity.Post;
import com.naver.campushackday.contentsproxyblog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naver.campushackday.contentsproxyblog.entity.BlogSetting;
import com.naver.campushackday.contentsproxyblog.service.BlogService;

import java.util.List;

@Controller
public class HomeController {

	private final BlogService blogService;
	private final PostService postService;

	public HomeController(BlogService blogService, PostService postService) {
		this.blogService = blogService;
		this.postService = postService;
	}

	@GetMapping("/")
	public ModelAndView home() {
		BlogSetting setting = blogService.findSetting();
		List<PostDto> posts = postService.findSortedPostsByViewCount();
		return new ModelAndView("index")
				.addObject("setting", setting)
				.addObject("posts", posts);
	}
}
