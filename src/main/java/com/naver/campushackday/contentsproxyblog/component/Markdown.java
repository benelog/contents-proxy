package com.naver.campushackday.contentsproxyblog.component;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.stereotype.Component;

@Component
public class Markdown {

    private MutableDataSet options;
    private Parser parser;

    public Markdown() {
        options = new MutableDataSet();
        parser = Parser.builder(options).build();
    }

    public String renderMarkdownTextToHtml(String markdownText) {
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        Node document = parser.parse(markdownText);
        String html = renderer.render(document);  // "<p>This is <em>HackDay</em></p>\n"
        return html;
    }
}
