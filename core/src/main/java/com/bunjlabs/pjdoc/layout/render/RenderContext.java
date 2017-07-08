package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.PjContentStream;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class RenderContext {

    private final PDDocument pDDocument;
    private final List<PjContentStream> contentStreams;

    public RenderContext(PDDocument pDDocument, List<PjContentStream> contentStreams) {
        this.pDDocument = pDDocument;
        this.contentStreams = contentStreams;
    }

    public PjContentStream getPageContentStream(int pageNumber) {
        return contentStreams.get(pageNumber);
    }

    public PDDocument getPDDocument() {
        return pDDocument;
    }

}
