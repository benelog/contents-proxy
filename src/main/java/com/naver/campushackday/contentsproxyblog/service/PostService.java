package com.naver.campushackday.contentsproxyblog.service;

import com.naver.campushackday.contentsproxyblog.component.GithubMarkdownLoader;
import com.naver.campushackday.contentsproxyblog.component.MarkdownParser;
import com.naver.campushackday.contentsproxyblog.entity.ImageType;
import com.naver.campushackday.contentsproxyblog.entity.Post;
import com.naver.campushackday.contentsproxyblog.exception.NoSuchPostException;
import com.naver.campushackday.contentsproxyblog.persistence.PostRepository;
import com.naver.campushackday.contentsproxyblog.util.StringParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

	private static final String NO_SUCH_POST_EXCEPTION_MESSAGE = "id 에 해당하는 post가 없습니다.";

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
	public Post findPost(long id) throws Exception {
		Post post = findOne(id);

		updateViewCount(post);
		post.setContent(getContentByUrl(post.getUrl()));
		return post;
	}

	public List<Post> findSortedPostsByViewCount() {
		List<Post> posts = postRepository.findAll();
		posts.sort((p1, p2) -> p2.getViewCount().compareTo(p1.getViewCount()));
		return posts;
	}

	public long savePost(Post post) {
		return postRepository.save(post).getId();
	}

	private void updateViewCount(Post post) {
		long updatedViewCount = post.getViewCount() + 1;
		post.setViewCount(updatedViewCount);
	}

	private String getContentByUrl(String url) throws Exception {
		String[] urlElements = StringParser.parseGithubUrl(url);
		String repoUrl = urlElements[0];
		String filePath = urlElements[1];
		String markdownText = githubMarkdownLoader.fetchMarkdownFileAndConvertToString(repoUrl, filePath);
		String markdownHtml = markdownParser.renderMarkdownTextToHtml(iterateImageUrls(repoUrl, markdownText));
		return markdownHtml;
	}

	private String iterateImageUrls(String repoUrl, String markdownText) {
		for (String imageUrl : StringParser.parseMarkdownImageUrl(markdownText)) {
			markdownText = convertImagePath(repoUrl, markdownText, imageUrl);
		}
		return markdownText;
	}

	private String convertImagePath(String repoUrl, String markdownText, String imageUrl) {
		if (isImage(imageUrl)) {
			if (checkAbsolutePath(imageUrl)) {
				markdownText = markdownText.replace(imageUrl, repoUrl + "/raw/master" + imageUrl);
			}
		}
		return markdownText;
	}

	private boolean checkAbsolutePath(String imageUrl) {
		return !imageUrl.contains("http");
	}

	private boolean isImage(String imageUrl) {
		return imageUrl.contains(ImageType.PNG.toString()) || imageUrl.contains(ImageType.JPEG.toString()) || imageUrl.contains(ImageType.JPG.toString());
	}
}
