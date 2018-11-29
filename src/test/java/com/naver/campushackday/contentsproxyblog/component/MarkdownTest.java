package com.naver.campushackday.contentsproxyblog.component;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MarkdownTest {

    private MarkdownParser markdown;

    @Before
    public void setUp() {
        markdown = new MarkdownParser();
    }

    @Test
    public void renderMarkdownTextToHtmlTest() {
        assertThat(markdown.renderMarkdownTextToHtml("This is *Sparta*")).isEqualTo("<p>This is <em>Sparta</em></p>\n");
    }
}
