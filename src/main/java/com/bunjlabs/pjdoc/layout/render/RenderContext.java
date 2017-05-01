package com.bunjlabs.pjdoc.layout.render;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class RenderContext {

    private final PDPageContentStream pageContentStream;

    public RenderContext(PDPageContentStream pageContentStream) {
        this.pageContentStream = pageContentStream;
    }

    public PDPageContentStream getPageContentStream() {
        return pageContentStream;
    }

}
