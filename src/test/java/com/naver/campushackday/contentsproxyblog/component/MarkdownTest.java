package com.naver.campushackday.contentsproxyblog.component;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class MarkdownTest {

    @Autowired
    private Markdown markdown;

    @Before
    public void setUp() {
        markdown = new Markdown();
    }

    @Test
    public void renderMarkdownTextToHtmlTest() {
        assertThat(markdown.renderMarkdownTextToHtml("This is *Sparta*")).isEqualTo("<p>This is <em>Sparta</em></p>\n");
    }
}
