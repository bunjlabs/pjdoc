package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.elements.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class DocumentRenderer {

    private final LinkedList<PDPage> pages = new LinkedList<>();
    private final LinkedList<PDPageContentStream> pagesContentStream = new LinkedList<>();

    private final List<Renderer> childRenderers;

    private final PDDocument pDDocument;
    private final Document document;

    public DocumentRenderer(PDDocument pDDocument, Document document) {
        this.pDDocument = pDDocument;
        this.document = document;

        this.childRenderers = new ArrayList<>(this.document.getChildren().size());

        this.document.getChildren().forEach((e) -> childRenderers.add(RendererFactory.createRendererSubtree(e)));
    }

    public void render() throws IOException {
        addPage();

        LayoutContext layoutContext = new LayoutContext(document.getEffectiveArea());
        RenderContext renderContext = new RenderContext(pDDocument, pagesContentStream.getLast());

        for (int childInx = 0; childInx < childRenderers.size(); childInx++) {
            Renderer renderer = childRenderers.get(childInx);

            LayoutResult layoutResult = renderer.render(renderContext, layoutContext);

            if (layoutResult.getType() == LayoutResult.PARTIAL) {
                addPage();

                layoutContext = new LayoutContext(document.getEffectiveArea());
                renderContext = new RenderContext(pDDocument, pagesContentStream.getLast());

                childInx--;
            }
        }

        for (PDPageContentStream contentStream : pagesContentStream) {
            contentStream.close();
        }

        for (PDPage page : pages) {
            pDDocument.addPage(page);
        }
    }

    private void addPage() throws IOException {
        PDPage page = new PDPage(document.getPageSize());

        PDPageContentStream pageContentStream = new PDPageContentStream(pDDocument, page, PDPageContentStream.AppendMode.APPEND, true);

        pages.add(page);
        pagesContentStream.add(pageContentStream);
    }
}
