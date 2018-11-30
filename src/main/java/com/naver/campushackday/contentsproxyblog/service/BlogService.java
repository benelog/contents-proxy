package com.naver.campushackday.contentsproxyblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.naver.campushackday.contentsproxyblog.entity.BlogSetting;
import com.naver.campushackday.contentsproxyblog.persistence.BlogSettingRepository;

@Service
public class BlogService {

	private static final Long GITHUB_BLOG_ID = 1L;

	private final BlogSettingRepository repository;

	public BlogService(BlogSettingRepository repository) {
		this.repository = repository;
	}

	public BlogSetting findSetting() {
		List<BlogSetting> settings = repository.findAll();

		if (settings != null && !settings.isEmpty()) {
			return settings.get(0);
		}

		var setting = new BlogSetting();
		setting.setTitle("제목 없는 블로그");
		return setting;
	}

	public String findGithubBlogTitle() {
		return repository.findTitleById(GITHUB_BLOG_ID);
	}

	public void updateBlogName(String newTitle) {
		BlogSetting setting = new BlogSetting();

		setting.setTitle(newTitle);
		setting.setId(GITHUB_BLOG_ID);

		repository.save(setting);
	}
}
