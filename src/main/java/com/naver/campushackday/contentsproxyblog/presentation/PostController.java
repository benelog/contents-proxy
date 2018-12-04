package com.naver.campushackday.contentsproxyblog.presentation;

import com.naver.campushackday.contentsproxyblog.dto.PostDto;
import com.naver.campushackday.contentsproxyblog.entity.Post;
import com.naver.campushackday.contentsproxyblog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/post/form")
	public String postForm() {
		return "postForm";
	}

	@GetMapping("/post/{postId}")
	public ModelAndView post(@PathVariable long postId) throws Exception {
		PostDto post = postService.findPost(postId);

		return new ModelAndView("post")
				.addObject("post", post);
	}

	@PostMapping("/post")
	public String addPost(Post post) {
		return "redirect:post/" + postService.savePost(post);
	}
}
