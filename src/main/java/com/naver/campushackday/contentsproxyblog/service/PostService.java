package com.naver.campushackday.contentsproxyblog.service;

import com.naver.campushackday.contentsproxyblog.component.GithubMarkdownLoader;
import com.naver.campushackday.contentsproxyblog.component.MarkdownParser;
import com.naver.campushackday.contentsproxyblog.entity.Post;
import com.naver.campushackday.contentsproxyblog.exception.NoSuchPostException;
import com.naver.campushackday.contentsproxyblog.persistence.PostRepository;
import com.naver.campushackday.contentsproxyblog.util.StringParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	private Post findOne(long id) {
		return postRepository.findById(id).orElseThrow(() -> new NoSuchPostException("id 에 해당하는 post가 없습니다."));
	}

	@Transactional
	public Post findPost(long id) throws Exception {
		Post post = findOne(id);

		updateViewCount(post);

		String[] urlElements = StringParser.parseGithubUrl(post.getUrl());
		String repoUrl = urlElements[0];
		String filePath = urlElements[1];
		String markdownText = githubMarkdownLoader.fetchMarkdownFileAndConvertToString(repoUrl, filePath);
		String markdownHtml = markdownParser.renderMarkdownTextToHtml(markdownText);
		post.setContent(markdownHtml);

		return post;
	}

	public List<Post> findSortedPostsByViewCount() {
		List<Post> posts = postRepository.findAll();
		posts.sort((p1, p2) -> p2.getViewCount().compareTo(p1.getViewCount()));
		return posts;
	}

	public long post(Post post) {
		return postRepository.save(post).getId();
	}

	private void updateViewCount(Post post) {
		long updatedViewCount = post.getViewCount() + 1;
		post.setViewCount(updatedViewCount);
	}

}
