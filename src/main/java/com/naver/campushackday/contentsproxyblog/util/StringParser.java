package com.naver.campushackday.contentsproxyblog.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {
	private static final String URL_SPLITOR = "/blob/master";
	private static final String IMAGE_TAG_SPLITOR = "(";
	private static final Pattern IMAGE_URL_PATTERN = Pattern.compile("(?:!\\[(.*?)\\]\\((.*?)\\))");

	public static String[] parseGithubUrl(String url) {
		return url.split(URL_SPLITOR);
	}

	public static List<String> parseMarkdownImageUrl(String markdownText) {
		List<String> allMatches = new ArrayList<>();

		matchRegex(markdownText, allMatches);
		return allMatches;
	}

	private static void matchRegex(String markdownText, List<String> allMatches) {
		Matcher m = IMAGE_URL_PATTERN.matcher(markdownText);

		while (m.find()) {
			String imageTag = m.group();
			String imageUrl = imageTag.substring(imageTag.indexOf(IMAGE_TAG_SPLITOR) + 1, imageTag.length() - 1);
			allMatches.add(imageUrl);
		}
	}
}
