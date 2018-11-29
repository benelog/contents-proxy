package com.naver.campushackday.contentsproxyblog.component;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.util.EncodingUtils;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class GithubMarkdownLoader {

	private ContentsService contentsService = new ContentsService();

	public String fetchMarkdownFileAndConvertToString(String repositoryURL, String filePath) throws Exception {
		return convertMarkdownByteArrayToString(fetchGithubMarkdownFile(repositoryURL, filePath));
	}

	private RepositoryContents fetchGithubMarkdownFile(String repositoryURL, String filePath) throws Exception {
		String path = filePath.replace(repositoryURL, "");
		return contentsService.getContents(RepositoryId.createFromUrl(repositoryURL), path).get(0);
	}

	private String convertMarkdownByteArrayToString(RepositoryContents mdFile) {
		return new String(EncodingUtils.fromBase64(mdFile.getContent()), StandardCharsets.UTF_8);
	}

}
