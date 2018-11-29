package com.naver.campushackday.contentsproxyblog.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringParserTest {

	@Test
	public void URL_PARSE_TEST() {
		String[] urlElements = StringParser.parseGithubUrl("https://github.com/naver/pinpoint/blob/master/.github/ISSUE_TEMPLATE/bug_report.md");
		assertThat(urlElements[0], is("https://github.com/naver/pinpoint"));
		assertThat(urlElements[1], is("/.github/ISSUE_TEMPLATE/bug_report.md"));
	}
}
