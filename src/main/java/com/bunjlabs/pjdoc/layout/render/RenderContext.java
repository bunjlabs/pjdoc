package com.bunjlabs.pjdoc.layout.render;

import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class RenderContext {

    private final PDDocument pDDocument;
    private final List<PDPageContentStream> pageContentStreams;

    public RenderContext(PDDocument pDDocument, List<PDPageContentStream> pageContentStreams) {
        this.pDDocument = pDDocument;
        this.pageContentStreams = pageContentStreams;
    }

    public PDPageContentStream getPageContentStream(int pageNumber) {
        return pageContentStreams.get(pageNumber);
    }

    public PDDocument getPDDocument() {
        return pDDocument;
    }

}
