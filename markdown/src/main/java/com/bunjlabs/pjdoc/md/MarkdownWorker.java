package com.bunjlabs.pjdoc.md;

import com.bunjlabs.pjdoc.layout.elements.Document;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    public Document work(File f) throws IOException, FileNotFoundException {
        return work(new FileInputStream(f));
    }

    public Document work(InputStream is) throws IOException {
        Document document = new Document();

        Node root = parser.parseReader(new InputStreamReader(is));

        MarkdownRenderer markdownRenderer = new MarkdownRenderer(document);
        markdownRenderer.render(root);

        return document;
    }
}
