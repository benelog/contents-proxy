package com.naver.campushackday.contentsproxyblog.presentation;

import com.naver.campushackday.contentsproxyblog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/blog")
public class BlogSettingController {
    private final BlogService blogService;

    public BlogSettingController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ModelAndView getBlogSetting() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("title", blogService.findSetting().getTitle());
        return modelAndView;
    }

    @PutMapping
    public ModelAndView modifyBlogTitle(String newTitle) {
        ModelAndView modelAndView = new ModelAndView();

        blogService.updateBlogName(newTitle);

        modelAndView.addObject("title", blogService.findGithubBlogTitle());
        return modelAndView;
    }
}

