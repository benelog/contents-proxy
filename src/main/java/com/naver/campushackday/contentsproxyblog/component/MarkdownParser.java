package com.naver.campushackday.contentsproxyblog.component;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.springframework.stereotype.Component;

@Component
public class MarkdownParser {

	private MutableDataSet options;
	private Parser parser;
	private HtmlRenderer renderer;

	public MarkdownParser() {
		options = new MutableDataSet();
		parser = Parser.builder(options).build();
		renderer = HtmlRenderer.builder(options).build();
	}

	public String renderMarkdownTextToHtml(String markdownText) {
		Node document = parser.parse(markdownText);
		return renderer.render(document);  // "<p>This is <em>HackDay</em></p>\n"
	}
}
