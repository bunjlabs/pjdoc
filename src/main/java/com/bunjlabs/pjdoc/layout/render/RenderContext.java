package com.bunjlabs.pjdoc.layout.render;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class RenderContext {

    private final PDDocument pDDocument;
    private final PDPageContentStream pageContentStream;

    public RenderContext(PDDocument pDDocument, PDPageContentStream pageContentStream) {
        this.pDDocument = pDDocument;
        this.pageContentStream = pageContentStream;
    }

    public PDPageContentStream getPageContentStream() {
        return pageContentStream;
    }

    public PDDocument getPDDocument() {
        return pDDocument;
    }

}
