package com.naver.campushackday.contentsproxyblog.service;

import com.naver.campushackday.contentsproxyblog.component.GithubMarkdownLoader;
import com.naver.campushackday.contentsproxyblog.component.MarkdownParser;
import com.naver.campushackday.contentsproxyblog.entity.Post;
import com.naver.campushackday.contentsproxyblog.exception.NoSuchPostException;
import com.naver.campushackday.contentsproxyblog.persistence.PostRepository;
import com.naver.campushackday.contentsproxyblog.util.StringParser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

	private final PostRepository postRepository;
	private final GithubMarkdownLoader githubMarkdownLoader;
	private final MarkdownParser markdownParser;

	public PostService(PostRepository postRepository, GithubMarkdownLoader githubMarkdownLoader, MarkdownParser markdownParser) {
		this.postRepository = postRepository;
		this.githubMarkdownLoader = githubMarkdownLoader;
		this.markdownParser = markdownParser;
	}

	public Post findOne(long id) {
		return postRepository.findById(id).orElseThrow(() -> new NoSuchPostException("id 에 해당하는 post가 없습니다."));

	}

	public Post findPost(long id) throws Exception {
		Post post = findOne(id);
		String[] urlElements = StringParser.parseGithubUrl(post.getUrl());
		String repoUrl = urlElements[0];
		String filePath = urlElements[1];
		String markdownText = githubMarkdownLoader.fetchMarkdownFileAndConvertToString(repoUrl, filePath);
		String markdownHtml = markdownParser.renderMarkdownTextToHtml(markdownText);
		post.setContent(markdownHtml);
		return post;
	}

	public List<Post> findPosts() {
		return postRepository.findAll();
	}

	public long save(Post post) {
		return postRepository.save(post).getId();
	}

}
