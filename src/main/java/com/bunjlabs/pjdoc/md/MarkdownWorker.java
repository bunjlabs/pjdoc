package com.bunjlabs.pjdoc.md;

import com.bunjlabs.pjdoc.layout.elements.Document;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class MarkdownWorker {

    private final Parser parser;

    public MarkdownWorker() {
        MutableDataSet options = new MutableDataSet();
        parser = Parser.builder(options).build();
    }

    public void work(Document document, Reader reader) throws IOException {
        Node root = parser.parseReader(reader);

        MarkdownRenderer markdownRenderer = new MarkdownRenderer(document);
        markdownRenderer.render(root);
    }
}
