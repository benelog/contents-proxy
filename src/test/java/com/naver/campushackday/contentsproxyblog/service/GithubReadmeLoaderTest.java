package com.naver.campushackday.contentsproxyblog.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@Slf4j
public class GithubReadmeLoaderTest {

    private GithubReadmeLoader loader;

    @Before
    public void setUp() throws Exception {
        loader = new GithubReadmeLoader();
    }

    @Test
    public void FETCH_GITHUB_MARKDOWN_FILE_TEST() throws Exception {
        String mdFileString = loader.fetchMarkdownFileAndConvertToString("https://github.com/jiwonbabe/my-daily-feed", "/README.md");
        String result = "# my-daily-feed [![Build Status](https://travis-ci.org/brilliantBae/my-daily-feed.svg?branch=master)](https://travis-ci.org/brilliantBae/my-daily-feed)\n" +
                "My daily feed project\n\n\n";
        assertThat(mdFileString, is(result));
    }

}