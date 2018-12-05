package com.naver.campushackday.contentsproxyblog.service;

import com.naver.campushackday.contentsproxyblog.component.GithubMarkdownLoader;
import com.naver.campushackday.contentsproxyblog.component.MarkdownParser;
import com.naver.campushackday.contentsproxyblog.dto.PostDto;
import com.naver.campushackday.contentsproxyblog.entity.ImageType;
import com.naver.campushackday.contentsproxyblog.entity.Post;
import com.naver.campushackday.contentsproxyblog.exception.NoSuchPostException;
import com.naver.campushackday.contentsproxyblog.persistence.PostRepository;
import com.naver.campushackday.contentsproxyblog.util.StringParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

	private static final String NO_SUCH_POST_EXCEPTION_MESSAGE = "ID 에 해당하는 POST가 없습니다.";
	private static final String ABSOLUTE_PATH_CHECKER = "http";
	private static final String IMAGE_URL_ELEMENT = "/raw/master";

	private final PostRepository postRepository;
	private final GithubMarkdownLoader githubMarkdownLoader;
	private final MarkdownParser markdownParser;

	public PostService(PostRepository postRepository, GithubMarkdownLoader githubMarkdownLoader, MarkdownParser markdownParser) {
		this.postRepository = postRepository;
		this.githubMarkdownLoader = githubMarkdownLoader;
		this.markdownParser = markdownParser;
	}

	private Post findOne(long id) {
		return postRepository.findById(id).orElseThrow(() -> new NoSuchPostException(NO_SUCH_POST_EXCEPTION_MESSAGE));
	}

	@Transactional
	public PostDto findPost(long id) throws Exception {
		Post post = findOne(id);
		updateViewCount(post);
		post.setContent(getContentByUrl(post.getUrl()));
		return new PostDto(post);
	}

	public List<PostDto> findSortedPostsByViewCount() {
		return postRepository.findAll()
				.stream()
				.map(PostDto::new)
				.sorted(Comparator.comparing(PostDto::getViewCount).reversed())
				.collect(Collectors.toList());
	}

	@Transactional
	public long savePost(Post post) {
		post.setCreatedDate(LocalDateTime.now());
		return postRepository.save(post).getId();
	}

	private void updateViewCount(Post post) {
		post.setViewCount(post.getViewCount() + 1);
	}

	private String getContentByUrl(String url) throws Exception {
		String[] urlElements = StringParser.parseGithubUrl(url);
		String repoUrl = urlElements[0];
		String filePath = urlElements[1];
		String markdownText = githubMarkdownLoader.fetchMarkdownFileAndConvertToString(repoUrl, filePath);
		return markdownParser.renderMarkdownTextToHtml(iterateImageUrls(repoUrl, markdownText));
	}

	private String iterateImageUrls(String repoUrl, String markdownText) {
		for (String imageUrl : StringParser.parseMarkdownImageUrl(markdownText)) {
			markdownText = convertImagePath(repoUrl, markdownText, imageUrl);
		}
		return markdownText;
	}

	private String convertImagePath(String repoUrl, String markdownText, String imageUrl) {
		if (isImage(imageUrl)) {
			if (isRelativePath(imageUrl)) {
				markdownText = markdownText.replace(imageUrl, repoUrl + IMAGE_URL_ELEMENT + imageUrl);
			}
		}
		return markdownText;
	}

	private boolean isRelativePath(String imageUrl) {
		return !imageUrl.contains(ABSOLUTE_PATH_CHECKER);
	}

	private boolean isImage(String imageUrl) {
		return imageUrl.contains(ImageType.PNG.toString()) || imageUrl.contains(ImageType.JPEG.toString()) || imageUrl.contains(ImageType.JPG.toString());
	}
}
